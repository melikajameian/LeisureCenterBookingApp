package presentation.console;

import java.util.Scanner;

public class BookingMenu {
    public static void showBookingMenu(Scanner scanner) {
        ConsoleMessages.showSelectOptionMessage("Booking menu");
        System.out.println("1- Book a class\n2- Attend a class\n3-Change a class\n4- Cancel a class\n5- Write a review");
        String bookingMenuInput = scanner.nextLine();
        switch (bookingMenuInput){
            case "1":
                bookClassByOptions();
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
            case "4":
                System.out.println("4");
                break;
            case "5":
                System.out.println("5");
                break;
            default :
                ConsoleMessages.showWrongInputMessage("between 1-5");
        }
    }
    public static void bookClassByOptions() {
        System.out.println("1- Book by lesson\n2- Book by time slots");
    }
}
