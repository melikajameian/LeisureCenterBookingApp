import java.util.Scanner;

public class LeisureCenterBookingMain {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        showWelcomingText();

        while (true){
            showSelectOptionMessage("");
            System.out.println("1- Booking menu\n2- Report menu");
        String input = scanner.nextLine();

        if(input.equals("1")) {
            showBookingMenu(scanner);
            break;
        }
        else if(input.equals("2")) {
            showReportMenu();
            break;
        }
        else
            showWrongInputMessage("1 or 2");
        }
        scanner.close();

    }

    public static void showSelectOptionMessage(String intro) {
        if(!intro.isEmpty())System.out.println("\n⫘⫘⫘⫘ "+intro+" ⫘⫘⫘⫘");
        System.out.println("Select an option: ");

    }

    public static void showWrongInputMessage(String optionRange) {
        System.out.println("wrong input! you should type "+ optionRange +", try again.");
    }

    public static void showReportMenu() {
        System.out.println("1- Monthly lesson report\n2- Monthly champion lesson type report");
    }

    public static void showWelcomingText() {
        System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘                                 ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
        System.out.println("˶ᵔ ᵕ ᵔ˶ Welcome to The Furzefield Leisure Centre booking program. ˶ᵔ ᵕ ᵔ˶");
        System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘                                 ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘\n");
    }

    public static void showBookingMenu(Scanner scanner) {
        showSelectOptionMessage("Booking menu");
        System.out.println("1- Book a class\n2- Attend a class\n3-Change a class\n4- Cancel a class\n5- Write a review");
        String bookingMenuInput = scanner.nextLine();
        switch (bookingMenuInput){
            case "1":
                bookClass();
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
                showWrongInputMessage("between 1-5");
        }
    }

    public static void bookClass() {
        System.out.println("1- Book by lesson\n2- Book by time slots");
    }
}
