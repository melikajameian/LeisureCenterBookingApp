package presentation.console;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;


    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        ConsoleMessages.showWelcomingText();

        while (true){
            ConsoleMessages.showSelectOptionMessage("");
            System.out.println("1- Booking menu\n2- Report menu");
            String input = scanner.nextLine();

            if(input.equals("1")) {
                BookingMenu.showBookingMenu(scanner);
                break;
            }
            else if(input.equals("2")) {
                ReportMenu.showReportMenu();
                break;
            }
            else
                ConsoleMessages.showWrongInputMessage("1 or 2");
        }
    }


}
