package presentation.console.utils;

public class ConsoleMessages {
    public static void showWelcomingText() {
        ConsoleTextUtils.printInYellow("====================                                 ====================");
        ConsoleTextUtils.printInYellow("˶ᵔ ᵕ ᵔ˶ Welcome to The Furzefield Leisure Centre booking program. ˶ᵔ ᵕ ᵔ˶");
        ConsoleTextUtils.printInYellow("====================                                 ====================\n");
    }

    public static void showIntroText(String intro) {
        ConsoleTextUtils.printInYellow("\n======= "+intro+" =======");    }

    public static void showSelectOptionMessage(String intro) {
        if(!intro.isEmpty())ConsoleTextUtils.printInYellow("\n======= "+intro+" =======");
        ConsoleTextUtils.printInYellow("Select an option: ");

    }

    public static void showWrongInputMessage(String optionRange) {
        ConsoleTextUtils.printInRed("wrong input! you should type "+ optionRange +", try again."+ "\n");
    }

    public static void showBackOption() {
        ConsoleTextUtils.printInYellow("0- back");
    }
}
