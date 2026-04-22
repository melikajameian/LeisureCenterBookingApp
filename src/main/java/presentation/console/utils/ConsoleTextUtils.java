package presentation.console.utils;

public class ConsoleTextUtils {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";


    public static void printInRed(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printInYellow(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    public static void printInGreen(String message) {
        System.out.println(GREEN + message + RESET);
    }
}
