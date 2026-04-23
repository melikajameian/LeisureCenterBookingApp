package presentation.console.Report;

import application.services.ReportService;

import java.util.Scanner;

public class ReportMainMenu {
    public ReportMainMenu(Scanner scanner, ReportService reportService) {
        showReportMenu(scanner,reportService);
    }

    public static void showReportMenu(Scanner  scanner, ReportService reportService) {
        System.out.println("1- Monthly lesson report\n2- Monthly champion lesson type report");
        new MonthlyLessonReport(scanner,reportService);
    }
}
