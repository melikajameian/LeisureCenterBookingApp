package presentation.console.Booking;

import application.services.BookingService;
import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Member;
import domain.entities.Session;
import domain.enums.DayOfWeek;
import presentation.console.utils.ConsoleMessages;
import presentation.console.utils.ConsoleTextUtils;
import presentation.console.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class CreateBooking {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final BookingService bookingService;

    public CreateBooking(SessionService sessionService, LessonService lessonService, Scanner scanner,
                         BookingService bookingService, Member member, String bookId) {

        this.sessionService = sessionService;
        this.bookingService = bookingService;
        this.scanner = scanner;

        var sessions = sessionService.getSessions();
        var lessons = lessonService.getLessons();

        bookClassByOptions(member, sessions, lessons, bookId);
    }

    public void bookClassByOptions(Member member, List<Session> sessions, List<Lesson> lessons, String bookingId) {
        while (true) {

            if(bookingId==null) ConsoleMessages.showSelectOptionMessage("Booking for " + member.toString());

            else ConsoleMessages.showSelectOptionMessage("Changing Booking for " + member.toString());

            System.out.println("1- Book by lesson\n2- Book by date time");
            ConsoleMessages.showBackOption();
            String classByOptionInput = scanner.nextLine();
            var inputNumber = MenuUtils.catchNumberFormatException(classByOptionInput, sessions.size());

            if (inputNumber == 1) BookByLessonMenu(lessons, sessions, member, bookingId);

            else if (inputNumber == 2) BookByDayMenu(sessions, member, lessons, bookingId);

            else break;

        }

    }

    private void BookByDayMenu(List<Session> sessions, Member member, List<Lesson> lessons, String bookingId) {

        while (true) {
            ConsoleMessages.showSelectOptionMessage("Book by Day");
            int counter=1;
            for(DayOfWeek dayOfWeek : DayOfWeek.values()) {
            System.out.println(counter+"- "+dayOfWeek.name());
                counter++;
            }
            ConsoleMessages.showBackOption();

            String input = scanner.nextLine();
            int choice = MenuUtils.catchNumberFormatException(input, 2);

            if (choice == -1) break;
            if (choice == 0) return;

            DayOfWeek selectedDay = (choice == 1) ? DayOfWeek.SATURDAY : DayOfWeek.SUNDAY;

            List<Session> filteredSessions = sessionService.getSessionsByTheDayOfWeek(selectedDay);

            for (Session session : filteredSessions) {
                System.out.println(filteredSessions.indexOf(session)+1 + "- " + session.toString());
            }

            ConsoleMessages.showBackOption();
            input = scanner.nextLine();

            int sessionChoice = MenuUtils.catchNumberFormatException(input, filteredSessions.size());

            if (sessionChoice == -1) continue;
            if (sessionChoice == 0) return;

            bookForMember(filteredSessions, member, sessionChoice - 1, bookingId);
            break;
        }
    }


    private void BookByLessonMenu(List<Lesson> lessons, List<Session> sessions, Member member, String bookingId) {
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
                BookByLessonMenu(lessons, sessions, member, bookingId);
            }
            if (selectedNumber == 0) return;
            else {
                int lessonIndex = selectedNumber - 1;
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
                    if (selectedOptionNumber == -1) break;
                    if (selectedOptionNumber == 0) return;
                    else {
                        int sessionIndex = selectedOptionNumber - 1;
                        bookForMember(selectedLessonSessions, member, sessionIndex, bookingId);
                        break;
                    }
                }
            }
        }
    }


    private void bookForMember(List<Session> sessions, Member member, int sessionIndex, String bookingId) {
        Session selectedSession = sessions.get(sessionIndex);
        if (selectedSession.isFull()) {
            ConsoleTextUtils.printInRed("The session is already full");
        }

        if (member == null) return;
        if (bookingId == null) {
            bookingService.create(member, selectedSession);
            ConsoleTextUtils.printInGreen("Booking has been created successfully, here's the detail:");
        }else{
            if (!bookingService.changeBookingsSession(bookingId,selectedSession)) {
                ConsoleTextUtils.printInRed("Cannot change an attended/cancelled booking");
            }
            ConsoleTextUtils.printInGreen("Booking has been changes successfully, here's the detail:");

        }
        System.out.println(member.toString());
        System.out.println(selectedSession.toString() + "\n");
    }


}
