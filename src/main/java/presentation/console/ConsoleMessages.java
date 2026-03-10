package presentation.console;

public class ConsoleMessages {
    public static void showWelcomingText() {
        System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘                                 ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
        System.out.println("˶ᵔ ᵕ ᵔ˶ Welcome to The Furzefield Leisure Centre booking program. ˶ᵔ ᵕ ᵔ˶");
        System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘                                 ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘\n");
    }
    public static void showSelectOptionMessage(String intro) {
        if(!intro.isEmpty())System.out.println("\n⫘⫘⫘⫘ "+intro+" ⫘⫘⫘⫘");
        System.out.println("Select an option: ");

    }

    public static void showWrongInputMessage(String optionRange) {
        System.out.println("wrong input! you should type "+ optionRange +", try again.");
    }
}
