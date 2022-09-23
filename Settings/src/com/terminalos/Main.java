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
        Settings();
    }
    // Main Settings Menu
    static void Settings() throws IOException, URISyntaxException, InterruptedException {
        cls();
        settingslogo();
        System.out.println("\n M > Return to Main Menu\n\n TerminalOSJ Settings:\n\n P > Reset TerminalOS Password\n S > System Information");
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String rawuserinput = Input.nextLine(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        String mainmenu = "M";
        String resetpass = "P";
        String SystemInfo = "S";
        if (userinput.equals(mainmenu)) {
            System.exit(0);
        }else if (userinput.equals(resetpass)) {
            //String fileName = "./TerminalOSJ/Settings/login.txt";
            String homeFolder = System.getProperty("user.home");
            PrintWriter writepass = new PrintWriter(homeFolder + "/TerminalOS-Data/Settings/login.txt", "UTF-8");
            writepass.print("");
            writepass.close();
            System.exit(0);
        }else if (userinput.equals(SystemInfo)) {
            SystemInfo();
        }else{
            Settings();
        }
    }
    // System Info
    static void SystemInfo() throws IOException, InterruptedException, URISyntaxException {
        cls();
        final String os = System.getProperty("os.name"); //detect OS
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        String cpuid = cpu.getProcessorIdentifier().getName();
        double diskSizeB = new File("/").getTotalSpace();
        String userName = System.getProperty("user.name");
        double memorySizeB = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        //memory math
        double memorySizeDivide = 1024;
        double memorySizeK = memorySizeB / memorySizeDivide;
        double memorySizeM = memorySizeK / memorySizeDivide;
        double memorysizeG = memorySizeM / memorySizeDivide;
        //disk math
        double diskSizeK = diskSizeB / memorySizeDivide;
        double diskSizeM = diskSizeK / memorySizeDivide;
        double diskSizeG = diskSizeM / memorySizeDivide;
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        System.out.println("       .7???????????" + Color.CYAN_BRIGHT + "    CPU Model: " + cpuid  + Color.RESET);
        System.out.println("        !!!!!!!!????" + Color.CYAN_BOLD_BRIGHT + "        - Core Count: " + Runtime.getRuntime().availableProcessors() + Color.RESET);
        System.out.println("               .7???" + Color.MAGENTA_BRIGHT + "    System Memory: " + Math.round(memorySizeM) + " MB" + Color.RESET);
        System.out.println("^^^^    :^^:   .7???" + Color.MAGENTA_BOLD_BRIGHT + "        - " + Math.round(memorysizeG) + " GB" + Color.RESET);
        System.out.println("????.  .????.  .????" + Color.YELLOW_BRIGHT + "    System Storage: " + Math.round(diskSizeM) + " MB" +Color.RESET);
        System.out.println("???7.   :^^^    ^^^^" + Color.YELLOW_BRIGHT + "        - " + Math.round(diskSizeG) + " GB" + Color.RESET);
        System.out.println("???7.               "+ Color.GREEN_BRIGHT + "    Host OS: " + operatingSystem.toString() + Color.RESET);
        System.out.println("????!!!!!!!!        ");
        System.out.println("???????????7.       ");
        seperator();
        if (os.contains("Windows")) {
            String homeFolder = System.getProperty("user.home");
            System.out.println("TerminalOS Directory: " + homeFolder + "\\TerminalOS");
        } else {
            String homeFolder = System.getProperty("user.home");
            System.out.println("TerminalOS Directory: " + homeFolder + "./TerminalOS");
        }
        System.out.println("Current User: " + userName);
        seperator();
        logo();
        getVersion();

        Scanner Input = new Scanner(System.in);  // User Input
        smolpause();
        System.out.println("\n  Press Enter to go back to Settings Menu > ");
        String rawuserinput = Input.nextLine(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        Settings();
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
    public static void loadingbar() {
        System.out.print("\r [                                 ]");
        smolpause2();
        System.out.print("\r [-                                ]");
        smolpause2();
        System.out.print("\r [--                               ]");
        smolpause2();
        System.out.print("\r [---                              ]");
        smolpause2();
        System.out.print("\r [----                             ]");
        smolpause2();
        System.out.print("\r [-----                            ]");
        smolpause2();
        System.out.print("\r [------                           ]");
        smolpause2();
        System.out.print("\r [-------                          ]");
        smolpause2();
        System.out.print("\r [-------                          ]");
        smolpause2();
        System.out.print("\r [--------                         ]");
        smolpause2();
        System.out.print("\r [---------                        ]");
        smolpause2();
        System.out.print("\r [----------                       ]");
        smolpause2();
        System.out.print("\r [-----------                      ]");
        smolpause2();
        System.out.print("\r [------------                     ]");
        smolpause2();
        System.out.print("\r [-------------                    ]");
        smolpause2();
        System.out.print("\r [--------------                   ]");
        smolpause2();
        System.out.print("\r [---------------                  ]");
        smolpause2();
        System.out.print("\r [----------------                 ]");
        smolpause2();
        System.out.print("\r [-----------------                ]");
        smolpause2();
        System.out.print("\r [------------------               ]");
        smolpause2();
        System.out.print("\r [-------------------              ]");
        smolpause2();
        System.out.print("\r [--------------------             ]");
        smolpause2();
        System.out.print("\r [---------------------            ]");
        smolpause2();
        System.out.print("\r [----------------------           ]");
        smolpause2();
        System.out.print("\r [-----------------------          ]");
        smolpause2();
        System.out.print("\r [------------------------         ]");
        smolpause2();
        System.out.print("\r [-------------------------        ]");
        smolpause2();
        System.out.print("\r [--------------------------       ]");
        smolpause2();
        System.out.print("\r [---------------------------      ]");
        smolpause2();
        System.out.print("\r [----------------------------     ]");
        smolpause2();
        System.out.print("\r [-----------------------------    ]");
        smolpause2();
        System.out.print("\r [------------------------------   ]");
        smolpause2();
        System.out.print("\r [-------------------------------  ]");
        smolpause2();
        System.out.print("\r [-------------------------------- ]");
        smolpause2();
        System.out.print("\r [---------------------------------]");
        smolpause2();
        System.out.println("\n Done");
        onesecondpause();
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
    public static void apps() throws IOException {
        //Creating a File object for directory
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS-Data/Applications");
        FilenameFilter batFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".jar")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String imageFilesList[] = directoryPath.list(batFilefilter);
        System.out.println(" Installed JAR Apps:");
        for(String fileName : imageFilesList) {
            System.out.println("   " + fileName);
        }
    }
    public static void bapps() throws IOException {

        //Creating a File object for directory
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS-Data/Applications");
        FilenameFilter batFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".bat")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String imageFilesList[] = directoryPath.list(batFilefilter);
        System.out.println(" Installed Batch Apps:");
        for(String fileName : imageFilesList) {
            System.out.println("   " + fileName);
        }
        System.out.println("");
    }
    public static void getVersion() throws IOException, InterruptedException {
        String homeFolder = System.getProperty("user.home");
        new File(homeFolder + "/TerminalOS-Data/Resources").mkdirs();
        try { //create version
            File login = new File(homeFolder + "/TerminalOS-Data/Resources/version.txt");
            if (login.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        BufferedReader brTest = new BufferedReader(new FileReader(homeFolder + "/TerminalOS-Data/Resources/version.txt"));
        String version = brTest.readLine();
        System.out.println("\n TerminalOS Version: " + Color.BLUE_BRIGHT + version + Color.RESET);
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
