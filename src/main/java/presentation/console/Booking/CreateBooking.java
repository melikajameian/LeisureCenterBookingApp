package presentation.console.Booking;

import application.services.BookingService;
import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Member;
import domain.entities.Session;
import presentation.console.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class CreateBooking {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final BookingService bookingService;

    public CreateBooking(SessionService sessionService, LessonService lessonService, Scanner scanner,
                         BookingService bookingService, MemberService memberService) {

        this.sessionService = sessionService;
        this.bookingService = bookingService;
        this.scanner = scanner;

        var sessions = sessionService.getSessions();
        var lessons = lessonService.getLessons();
        var members = memberService.getMembers();

        bookClassByOptions(members, sessions, lessons);
    }

    public void bookClassByOptions(List<Member> members, List<Session> sessions, List<Lesson> lessons) {
        while (true) {
            ConsoleMessages.showSelectOptionMessage("");
            System.out.println("1- Book by lesson\n2- Book by date time");
            ConsoleMessages.showBackOption();
            String classByOptionInput = scanner.nextLine();

            var inputNumber = MenuUtils.catchNumberFormatException(classByOptionInput, sessions.size());

            if (inputNumber == 1) {
                BookByLessonMenu(lessons, sessions, members);
            } else if (inputNumber == 2) {
                BookByDateMenu(sessions, members, lessons);
            }else {
                break;
            }

        }

    }

    private void BookByDateMenu(List<Session> sessions, List<Member> members, List<Lesson> lessons) {
        while (true) {
            for (Session session : sessions) {
                System.out.println(
                        sessions.indexOf(session) + 1 + "- " +
                                session.toString()
                );
            }
            ConsoleMessages.showBackOption();
            String input = scanner.nextLine();
            var inputNumber = MenuUtils.catchNumberFormatException(input, sessions.size());
            if (inputNumber == -1) {
                break;
            }
            if (inputNumber == 0) {
                return;
            } else {
                int sessionIndex = inputNumber-1;
                SelectUserToBookFor(sessions, members, sessionIndex);
                break;
            }

        }
    }


    private void BookByLessonMenu(List<Lesson> lessons, List<Session> sessions, List<Member> members) {
        while (true) {
            ConsoleMessages.showSelectOptionMessage("");
            System.out.println("*** (Prices are per session.)");
            for (Lesson lesson : lessons) {
                System.out.println(lessons.indexOf(lesson) + 1 + "- " + lesson.getLessonType().toString() + "     Price:" + lesson.getPrice());
            }
            ConsoleMessages.showBackOption();
            String selectedOption = scanner.nextLine();
            var selectedNumber = MenuUtils.catchNumberFormatException(selectedOption, lessons.size());
            if (selectedNumber == -1) {
                BookByLessonMenu(lessons, sessions, members);
            }
            if (selectedNumber == 0) {
                return;
            } else {
                int lessonIndex =selectedNumber-1;
                Lesson selectedLesson = lessons.get(lessonIndex);
                var selectedLessonSessions = sessionService.getSessionsByLesson(selectedLesson);
                while (true) {
                    System.out.println(selectedLesson.getLessonType().toString() + " sessions are as below");
                    ConsoleMessages.showSelectOptionMessage("");

                    int counter = 1;
                    for (Session session : selectedLessonSessions) {
                        System.out.println(counter + "- " + session.toString());
                        counter++;
                    }
                    ConsoleMessages.showBackOption();

                    selectedOption = scanner.nextLine();
                    var selectedOptionNumber = MenuUtils.catchNumberFormatException(selectedOption, sessions.size());
                    if (selectedOptionNumber == -1) {
                        break;
                    }
                    if (selectedOptionNumber == 0) {
                        return;
                    } else {
                        int sessionIndex = selectedOptionNumber-1;
                        SelectUserToBookFor(sessions, members, sessionIndex);
                        break;
                    }
                }
            }
        }
    }


    private void SelectUserToBookFor(List<Session> sessions, List<Member> members, int sessionIndex) {
        Session selectedSession = sessions.get(sessionIndex);
        if(selectedSession.isFull()){
            ConsoleTextUtils.printInRed("The session is already full");
        }

        MenuUtils.showMemberList(members, "(member you are booking for) \n");

        Member member = MenuUtils.findMember(members, scanner);
        if (member == null) return;
        bookingService.create(member, selectedSession);
        ConsoleTextUtils.printInGreen("Booking has been created successfully, here's the detail:");
        System.out.println(member.toString());
        System.out.println(selectedSession.toString() + "\n");
        return;
    }


}
