package presentation.console.Report;

import application.services.ReportService;
import domain.enums.Month;
import presentation.console.utils.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.Scanner;

public class MonthlyLessonReport {
    public MonthlyLessonReport(Scanner scanner, ReportService reportService) {
    run(scanner,reportService);
    }


    private void run(Scanner scanner, ReportService reportService){
        ConsoleMessages.showIntroText("Monthly lesson report");

        System.out.print("Enter month number (e.g., 1=>february ): ");
        var input = scanner.nextLine();
        int monthNumber = MenuUtils.catchNumberFormatException(input,Month.values().length);
        String result = reportService.getMonthlyLessonReport(monthNumber);

        ConsoleTextUtils.printInGreen(result);

    }
}
