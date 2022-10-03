package com.terminalos;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import java.awt.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import oshi.software.os.OperatingSystem;

import javax.swing.JFrame;

public class Main {
    // Managing Scripts
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        mainmenu();
    }
    //Main Menu Application
    static void mainmenu() throws IOException, InterruptedException, URISyntaxException { // Main Method
        cls();
        appstext();
        System.out.println("\n M > Return to Main Menu\n");
        System.out.println(" AMGR Functions:\n");
        System.out.println(" O > Open Applications folder in host OS\n");
        System.out.println(" Application Types:\n");
        System.out.println(" J > View Applications");
        System.out.println(" B > View BATCH Programs" + Color.RED + " -- Windows hosts only! --" + Color.RESET);
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String rawuserinput = Input.next(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        String exit = "M";
        String openappfolder = "O";
        String jar = "J";
        String batch = "B";
        if (userinput.equals(exit)) {
            System.exit(0);
        } else if (userinput.equals(openappfolder)) {
            String homeFolder = System.getProperty("user.home");
            File file = new File (homeFolder + "/TerminalOS-Data/Applications");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            mainmenu();
        } else if (userinput.equals(jar)) {
            nativeapps();
        } else if (userinput.equals(batch)) {
            batchprograms();
        } else {
            mainmenu();
        }
    }
    static void nativeapps() throws IOException, InterruptedException, URISyntaxException {
        cls();
        appstext();
        System.out.println("\n M > Return to Application Menu\n");
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS/Applications");
        FilenameFilter jarFilefilter = (dir, name) -> name.regionMatches(true, name.length() - 4, ".app", 0, 4);
        String filenames[] = directoryPath.list(jarFilefilter);
        System.out.println(" Installed Apps:");
        for (int i = 0; i < filenames.length; i++) {
            System.out.printf("%2d > %s%n", i + 1, filenames[i]);
        }
        //start user input
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String userinput0 = Input.next(); // Read user input
        //end user input
        int userinput1 = 0;
        try {
            userinput1 = Integer.parseInt(userinput0);
            if (userinput1 <= filenames.length) {
                String userinput = filenames[userinput1 - 1];
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                try {
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    new ProcessBuilder("cmd", "/c", "java -jar \"" + homeFolder + "\\TerminalOS\\Applications\\" + userinput).inheritIO().start().waitFor();
                    System.out.print(Color.RESET);
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    nativeapps();
                } catch (Exception e) {
                    nativeapps();
                }
            } else {
                try {
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    ProcessBuilder processBuilder = new ProcessBuilder();
                    processBuilder.command("bash", "-c", "java -jar " + homeFolder + "/TerminalOS/Applications/" + userinput).inheritIO().start().waitFor();
                    System.out.print(Color.RESET);
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    nativeapps();
                } catch (Exception e) {
                    nativeapps();
                }
            }
        } else {
                nativeapps();
            }
        } catch (NumberFormatException ex) {
        }
        String menu = "M";
        if (userinput0.equals(menu)) {
            mainmenu();
        } else {
            nativeapps();
        }
    }
    static void batchprograms() throws IOException, URISyntaxException, InterruptedException {
        cls();
        jarappascii();
        System.out.println("\n M > Return to Application Menu\n");
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS/Applications");
        FilenameFilter jarFilefilter = (dir, name) -> name.regionMatches(true, name.length() - 4, ".bat", 0, 4);
        String filenames[] = directoryPath.list(jarFilefilter);
        System.out.println(" Installed Apps:");
        for (int i = 0; i < filenames.length; i++) {
            System.out.printf("%2d > %s%n", i + 1, filenames[i]);
        }
        //start user input
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String userinput0 = Input.next(); // Read user input
        //end user input
        int userinput1 = 0;
        try {
            userinput1 = Integer.parseInt(userinput0);
            if (userinput1 <= filenames.length) {
                String userinput = filenames[userinput1 - 1]; ///PROBLEMATIC LINE
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    new ProcessBuilder("cmd", "/c", "call \"" + homeFolder + "\\TerminalOS\\Applications\\" + userinput + "\"").inheritIO().start().waitFor();
                    new ProcessBuilder("cmd", "/c", "title TerminalOS").inheritIO().start().waitFor();
                    System.out.print(Color.RESET);
                    cls();
                    logoTOSlogo();
                    System.out.println();
                    quickloadingbar();
                    cls();
                    batchprograms();
                } else {
                    try {
                        cls();
                        logoTOSlogo();
                        System.out.println();
                        quickloadingbar();
                        cls();
                        System.out.println(Color.RED + " -- Error running BATCH Program on non Windows OS --");
                        SystemInfo systemInfo = new SystemInfo();
                        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
                        System.out.println(" \"" + operatingSystem.toString() + "\" Cannot execute windows batch files.\n\n Returning to main menu..." + Color.RESET);
                        onesecondpause();
                        onesecondpause();
                        cls();
                        logoTOSlogo();
                        System.out.println();
                        quickloadingbar();
                        cls();
                        mainmenu();
                    } catch (Exception e) {
                        batchprograms();
                    }
                }
            } else {
                batchprograms();
            }
        } catch (NumberFormatException ex) {
        }
        String menu = "M";
        if (userinput0.equals(menu)) {
            mainmenu();
        } else {
            batchprograms();
        }
    }

    // -- LIBRARIES --
    // big ASCII

    // small ASCII
    public static void logo() {
        System.out.println(" _____                   _             _ _____ _____ ");
        System.out.println("|_   _|                 (_)           | |  _  /  ___|");
        System.out.println("  | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        System.out.println("  | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        System.out.println("  | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        System.out.println("  \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
    }
    public static void logoTOSlogo() {
        System.out.println("       .7???????????");
        System.out.println("        !!!!!!!!????     _____                   _             _ _____ _____ ");
        System.out.println("               .7???    |_   _|                 (_)           | |  _  /  ___|");
        System.out.println("^^^^    :^^:   .7???      | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        System.out.println("????.  .????.  .????      | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        System.out.println("???7.   :^^^    ^^^^      | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        System.out.println("???7.                     \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
        System.out.println("????!!!!!!!!        ");
        System.out.println("???????????7.       ");
    }
    public static void menulogo() {
        System.out.println("  __  __                  ");
        System.out.println(" |  \\/  |                 ");
        System.out.println(" | \\  / | ___ _ __  _   _ ");
        System.out.println(" | |\\/| |/ _ \\ '_ \\| | | |");
        System.out.println(" | |  | |  __/ | | | |_| |");
        System.out.println(" |_|  |_|\\___|_| |_|\\__,_|");
    }
    public static void animlogo() {
        smolpause();
        System.out.println(" _____                   _             _ _____ _____ ");
        smolpause();
        System.out.println("|_   _|                 (_)           | |  _  /  ___|");
        smolpause();
        System.out.println("  | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        smolpause();
        System.out.println("  | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        smolpause();
        System.out.println("  | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        smolpause();
        System.out.println("  \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
    }
    public static void animlogoTOSlogo2() {
        System.out.println("       .7???????????");
        smolpause2();
        System.out.println("        !!!!!!!!????     _____                   _             _ _____ _____ ");
        smolpause2();
        System.out.println("               .7???    |_   _|                 (_)           | |  _  /  ___|");
        smolpause2();
        System.out.println("^^^^    :^^:   .7???      | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        smolpause2();
        System.out.println("????.  .????.  .????      | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        smolpause2();
        System.out.println("???7.   :^^^    ^^^^      | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        smolpause2();
        System.out.println("???7.                     \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
        smolpause2();
        System.out.println("????!!!!!!!!        ");
        smolpause2();
        System.out.println("???????????7.       ");
    }
    static void settingslogo() {
        System.out.println("   _____      _   _   _                 ");
        System.out.println("  / ____|    | | | | (_)                ");
        System.out.println(" | (___   ___| |_| |_ _ _ __   __ _ ___ ");
        System.out.println("  \\___ \\ / _ \\ __| __| | '_ \\ / _` / __|");
        System.out.println("  ____) |  __/ |_| |_| | | | | (_| \\__ \\");
        System.out.println(" |_____/ \\___|\\__|\\__|_|_| |_|\\__, |___/");
        System.out.println("                               __/ |    ");
        System.out.println("                              |___/     ");
    }
    static void seperator() {
        System.out.println("--------------------------------------------------------------------------------");
    }
    static void appstext() {
        System.out.println("  ___                  ");
        System.out.println(" / _ \\                 ");
        System.out.println("/ /_\\ \\_ __  _ __  ___ ");
        System.out.println("|  _  | '_ \\| '_ \\/ __|");
        System.out.println("| | | | |_) | |_) \\__ \\");
        System.out.println("\\_| |_/ .__/| .__/|___/");
        System.out.println("      | |   | |        ");
        System.out.println("      |_|   |_|        ");
    }
    static void batchappascii() {
        System.out.println("  ___                  ");
        System.out.println(" / _ \\                 ");
        System.out.println("/ /_\\ \\_ __  _ __  ___     _          _  ");
        System.out.println("|  _  | '_ \\| '_ \\/ __|   | |__  __ _| |_ ");
        System.out.println("| | | | |_) | |_) \\__ \\  _| '_ \\/ _` |  _|");
        System.out.println("\\_| |_/ .__/| .__/|___/ (_)_.__/\\__,_|\\__|");
        System.out.println("      | |   | |        ");
        System.out.println("      |_|   |_|        ");
    }
    static void jarappascii() {
        System.out.println("  ___                  ");
        System.out.println(" / _ \\                 ");
        System.out.println("/ /_\\ \\_ __  _ __  ___ ");
        System.out.println("|  _  | '_ \\| '_ \\/ __|    (_)__ _ _ _ ");
        System.out.println("| | | | |_) | |_) \\__ \\  _ | / _` | '_|");
        System.out.println("\\_| |_/ .__/| .__/|___/ (_)/ \\__,_|_|  ");
        System.out.println("      | |   | |          |__/          ");
        System.out.println("      |_|   |_|        ");
    }
    public static void quickloadingbar() {
        System.out.print("\r [                                 ]");
        fasterrandompause();
        System.out.print("\r [-                                ]");
        fasterrandompause();
        System.out.print("\r [--                               ]");
        fasterrandompause();
        System.out.print("\r [---                              ]");
        fasterrandompause();
        System.out.print("\r [----                             ]");
        fasterrandompause();
        System.out.print("\r [-----                            ]");
        fasterrandompause();
        System.out.print("\r [------                           ]");
        fasterrandompause();
        System.out.print("\r [-------                          ]");
        fasterrandompause();
        System.out.print("\r [-------                          ]");
        fasterrandompause();
        System.out.print("\r [--------                         ]");
        fasterrandompause();
        System.out.print("\r [---------                        ]");
        fasterrandompause();
        System.out.print("\r [----------                       ]");
        fasterrandompause();
        System.out.print("\r [-----------                      ]");
        fasterrandompause();
        System.out.print("\r [------------                     ]");
        fasterrandompause();
        System.out.print("\r [-------------                    ]");
        fasterrandompause();
        System.out.print("\r [--------------                   ]");
        fasterrandompause();
        System.out.print("\r [---------------                  ]");
        fasterrandompause();
        System.out.print("\r [----------------                 ]");
        fasterrandompause();
        System.out.print("\r [-----------------                ]");
        fasterrandompause();
        System.out.print("\r [------------------               ]");
        fasterrandompause();
        System.out.print("\r [-------------------              ]");
        fasterrandompause();
        System.out.print("\r [--------------------             ]");
        fasterrandompause();
        System.out.print("\r [---------------------            ]");
        fasterrandompause();
        System.out.print("\r [----------------------           ]");
        fasterrandompause();
        System.out.print("\r [-----------------------          ]");
        fasterrandompause();
        System.out.print("\r [------------------------         ]");
        fasterrandompause();
        System.out.print("\r [-------------------------        ]");
        fasterrandompause();
        System.out.print("\r [--------------------------       ]");
        fasterrandompause();
        System.out.print("\r [---------------------------      ]");
        fasterrandompause();
        System.out.print("\r [----------------------------     ]");
        fasterrandompause();
        System.out.print("\r [-----------------------------    ]");
        fasterrandompause();
        System.out.print("\r [------------------------------   ]");
        fasterrandompause();
        System.out.print("\r [-------------------------------  ]");
        fasterrandompause();
        System.out.print("\r [-------------------------------- ]");
        fasterrandompause();
        System.out.print("\r [---------------------------------]");
        fasterrandompause();
    }
    public static void loadingbar() {
        System.out.print("\r [                                 ]");
        randompause();
        System.out.print("\r [-                                ]");
        randompause();
        System.out.print("\r [--                               ]");
        randompause();
        System.out.print("\r [---                              ]");
        randompause();
        System.out.print("\r [----                             ]");
        randompause();
        System.out.print("\r [-----                            ]");
        randompause();
        System.out.print("\r [------                           ]");
        randompause();
        System.out.print("\r [-------                          ]");
        randompause();
        System.out.print("\r [-------                          ]");
        randompause();
        System.out.print("\r [--------                         ]");
        randompause();
        System.out.print("\r [---------                        ]");
        randompause();
        System.out.print("\r [----------                       ]");
        randompause();
        System.out.print("\r [-----------                      ]");
        randompause();
        System.out.print("\r [------------                     ]");
        randompause();
        System.out.print("\r [-------------                    ]");
        randompause();
        System.out.print("\r [--------------                   ]");
        randompause();
        System.out.print("\r [---------------                  ]");
        randompause();
        System.out.print("\r [----------------                 ]");
        randompause();
        System.out.print("\r [-----------------                ]");
        randompause();
        System.out.print("\r [------------------               ]");
        randompause();
        System.out.print("\r [-------------------              ]");
        randompause();
        System.out.print("\r [--------------------             ]");
        randompause();
        System.out.print("\r [---------------------            ]");
        randompause();
        System.out.print("\r [----------------------           ]");
        randompause();
        System.out.print("\r [-----------------------          ]");
        randompause();
        System.out.print("\r [------------------------         ]");
        randompause();
        System.out.print("\r [-------------------------        ]");
        randompause();
        System.out.print("\r [--------------------------       ]");
        randompause();
        System.out.print("\r [---------------------------      ]");
        randompause();
        System.out.print("\r [----------------------------     ]");
        randompause();
        System.out.print("\r [-----------------------------    ]");
        randompause();
        System.out.print("\r [------------------------------   ]");
        randompause();
        System.out.print("\r [-------------------------------  ]");
        randompause();
        System.out.print("\r [-------------------------------- ]");
        randompause();
        System.out.print("\r [---------------------------------]");
        randompause();
    }
    // functions and enums
    static void cls() throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
    static void onesecondpause() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void randompause() {
        int random = (int)(Math.random() * 300 + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void fasterrandompause() {
        int random = (int)(Math.random() * 50 + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void shortpause() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void smolpause() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void smolpause2() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void shortpause2() {
        try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void TOSlogo() {
        System.out.println("       .7???????????");
        System.out.println("        !!!!!!!!????");
        System.out.println("               .7???");
        System.out.println("^^^^    :^^:   .7???");
        System.out.println("????.  .????.  .????");
        System.out.println("???7.   :^^^    ^^^^");
        System.out.println("???7.");
        System.out.println("????!!!!!!!!");
        System.out.println("???????????7.");
    }
    public static void animmenulogo() {
        System.out.println("  __  __                  ");
        smolpause2();
        System.out.println(" |  \\/  |                 ");
        smolpause2();
        System.out.println(" | \\  / | ___ _ __  _   _ ");
        smolpause2();
        System.out.println(" | |\\/| |/ _ \\ '_ \\| | | |");
        smolpause2();
        System.out.println(" | |  | |  __/ | | | |_| |");
        smolpause2();
        System.out.println(" |_|  |_|\\___|_| |_|\\__,_|");
    }

    enum Color {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),    // BLACK
        RED("\033[0;31m"),      // RED
        GREEN("\033[0;32m"),    // GREEN
        YELLOW("\033[0;33m"),   // YELLOW
        BLUE("\033[0;34m"),     // BLUE
        MAGENTA("\033[0;35m"),  // MAGENTA
        CYAN("\033[0;36m"),     // CYAN
        WHITE("\033[0;37m"),    // WHITE

        // Bold
        BLACK_BOLD("\033[1;30m"),   // BLACK
        RED_BOLD("\033[1;31m"),     // RED
        GREEN_BOLD("\033[1;32m"),   // GREEN
        YELLOW_BOLD("\033[1;33m"),  // YELLOW
        BLUE_BOLD("\033[1;34m"),    // BLUE
        MAGENTA_BOLD("\033[1;35m"), // MAGENTA
        CYAN_BOLD("\033[1;36m"),    // CYAN
        WHITE_BOLD("\033[1;37m"),   // WHITE

        // Underline
        BLACK_UNDERLINED("\033[4;30m"),     // BLACK
        RED_UNDERLINED("\033[4;31m"),       // RED
        GREEN_UNDERLINED("\033[4;32m"),     // GREEN
        YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
        BLUE_UNDERLINED("\033[4;34m"),      // BLUE
        MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
        CYAN_UNDERLINED("\033[4;36m"),      // CYAN
        WHITE_UNDERLINED("\033[4;37m"),     // WHITE

        // Background
        BLACK_BACKGROUND("\033[40m"),   // BLACK
        RED_BACKGROUND("\033[41m"),     // RED
        GREEN_BACKGROUND("\033[42m"),   // GREEN
        YELLOW_BACKGROUND("\033[43m"),  // YELLOW
        BLUE_BACKGROUND("\033[44m"),    // BLUE
        MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
        CYAN_BACKGROUND("\033[46m"),    // CYAN
        WHITE_BACKGROUND("\033[47m"),   // WHITE

        // High Intensity
        BLACK_BRIGHT("\033[0;90m"),     // BLACK
        RED_BRIGHT("\033[0;91m"),       // RED
        GREEN_BRIGHT("\033[0;92m"),     // GREEN
        YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
        BLUE_BRIGHT("\033[0;94m"),      // BLUE
        MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
        CYAN_BRIGHT("\033[0;96m"),      // CYAN
        WHITE_BRIGHT("\033[0;97m"),     // WHITE

        // Bold High Intensity
        BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
        RED_BOLD_BRIGHT("\033[1;91m"),      // RED
        GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
        YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
        BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
        MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
        CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
        WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

        // High Intensity backgrounds
        BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
        RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
        GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
        YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
        BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
        MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
        CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
        WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
