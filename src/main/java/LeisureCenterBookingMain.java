import application.services.LessonService;
import application.services.MemberService;
import infrastructure.factories.DataFactory;
import presentation.console.MainMenu;

import java.util.Scanner;


public class LeisureCenterBookingMain {
    public static void main(String[] args) {
        LessonService lessonService = new LessonService();
        MemberService memberService = new MemberService();

        DataFactory.initialize(lessonService,memberService);

        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner);


        mainMenu.start();

        scanner.close();
    }
}
