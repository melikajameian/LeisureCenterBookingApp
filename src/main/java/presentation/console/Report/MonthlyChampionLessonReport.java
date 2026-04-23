package presentation.console.Report;

import application.services.ReportService;
import domain.enums.Month;
import presentation.console.utils.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.Scanner;

public class MonthlyChampionLessonReport {
    public MonthlyChampionLessonReport(Scanner scanner, ReportService reportService) {
        ConsoleMessages.showIntroText("Monthly Champion Lesson Report");

        System.out.print("Enter month number (e.g., 1=>february ): ");
        var input = scanner.nextLine();
        int monthNumber = MenuUtils.catchNumberFormatException(input, Month.values().length);
        String result = reportService.getChampionReport(monthNumber);

        ConsoleTextUtils.printInGreen(result);
    }
}
