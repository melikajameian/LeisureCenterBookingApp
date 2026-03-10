import presentation.console.MainMenu;

import java.util.Scanner;


public class LeisureCenterBookingMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        MainMenu mainMenu = new MainMenu(scanner);

        mainMenu.start();

        scanner.close();
    }
}
