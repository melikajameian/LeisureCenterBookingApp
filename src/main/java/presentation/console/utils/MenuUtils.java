package presentation.console.utils;

import domain.entities.Member;
import org.jetbrains.annotations.Nullable;
import presentation.console.ConsoleMessages;

import java.util.List;
import java.util.Scanner;

public class MenuUtils {

    public static int catchNumberFormatException(String userInput, int listSize) {
        int number = -1;
        try {
            number = Integer.parseInt(userInput);

            if (number < 0 || number > listSize) {
                ConsoleMessages.showWrongInputMessage("a number between options available");
                return -1;
            }
        } catch (NumberFormatException e) {
            ConsoleMessages.showWrongInputMessage("a number");
        }


        return number;
    }

    public static @Nullable Member findMember(List<Member> members, Scanner scanner) {
        String inputString = scanner.nextLine();
        var inputNumber = MenuUtils.catchNumberFormatException(inputString, members.size());
        if (inputNumber == -1||inputNumber == 0) {
            return null;
        }
        int memberIndex = inputNumber-1;
        return members.get(memberIndex);
    }

    public static void showMemberList(List<Member> members,String intro) {
        ConsoleMessages.showSelectOptionMessage("Member");
        System.out.println(intro);
        for (Member m : members) {
            System.out.println(members.indexOf(m)+1 + " - " + (m.toString()));
        }
        ConsoleMessages.showBackOption();
    }
}
