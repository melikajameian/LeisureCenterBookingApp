package presentation.console.Report;

import application.services.ReportService;
import presentation.console.utils.ConsoleMessages;

import java.util.Scanner;

public class ReportMainMenu {
    public final Scanner scanner;
    public final ReportService reportService;

    public ReportMainMenu(Scanner scanner, ReportService reportService) {
        this.scanner = scanner;
        this.reportService = reportService;
        showReportMenu(this.scanner, this.reportService);
    }

    public void showReportMenu(Scanner scanner, ReportService reportService) {
        while (true) {
            ConsoleMessages.showSelectOptionMessage("Report Menu");
            System.out.println("1- Monthly lesson report\n2- Monthly champion lesson type report");
            ConsoleMessages.showBackOption();

            var input = scanner.nextLine();
            switch (input) {
                case "1" -> new MonthlyLessonReport(scanner, reportService);
                case "2" -> new MonthlyChampionLessonReport(scanner, reportService);
                case "0" -> {
                    return;
                }
                default -> ConsoleMessages.showWrongInputMessage("1-2 or 0");
            }
        }
    }
}
