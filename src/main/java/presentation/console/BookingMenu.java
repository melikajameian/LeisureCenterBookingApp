package presentation.console;

import application.services.BookingService;
import application.services.LessonService;
import application.services.MemberService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Member;
import domain.entities.Session;
import domain.entities.SessionDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;
    private final BookingService bookingService;
    private final MemberService memberService;

    public BookingMenu(SessionService sessionService, LessonService lessonService, Scanner scanner, BookingService bookingService, MemberService memberService) {
        this.sessionService = sessionService;
        this.lessonService = lessonService;
        this.bookingService = bookingService;
        this.memberService = memberService;
        this.scanner = scanner;
    }

    public void showBookingMenu() {
        ConsoleMessages.showSelectOptionMessage("Booking menu");
        System.out.println("1- Book a class\n2- Attend a class\n3-Change a class\n4- Cancel a class\n5- Write a review");
        String bookingMenuInput = scanner.nextLine();
        switch (bookingMenuInput) {
            case "1":
                bookClassByOptions();
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
            case "4":
                System.out.println("4");
                break;
            case "5":
                System.out.println("5");
                break;
            default:
                ConsoleMessages.showWrongInputMessage("between 1-5");
        }
    }

    public void bookClassByOptions() {
        ConsoleMessages.showSelectOptionMessage("");
        System.out.println("1- Book by lesson\n2- Book by date time");
        String classByOptionInput = scanner.nextLine();

        var sessions = sessionService.getSessions();
        var lessons = lessonService.getLessons();
        var members = memberService.getMembers();

        if (classByOptionInput.equals("1")) {
            BookByLessonMenu(lessons, sessions, members);
        } else if (classByOptionInput.equals("2")) {
            BookByDateMenu(sessions);
        } else {
            ConsoleMessages.showWrongInputMessage("1 or 2");
        }

    }

    private void BookByDateMenu(List<Session> sessions) {
        while (true) {
            int counter = 1;

            for (Session session : sessions) {
                System.out.println(
                        counter + "- " +
                                session.getSessionDate().getFullDateString() +
                                " | " + session.getLesson().getLessonType().toString() +
                                " | " + session.getTimeSlot()
                );
                counter++;
            }
            String number = scanner.nextLine();
            var sessionIndex = catchNumberFormatException(number, sessions.size());
            if (sessionIndex == -1) {
                break;
            }
            if (sessionIndex == 0) {
                bookClassByOptions();
            } else {
                //implement
            }

        }
    }

    private void BookByLessonMenu(List<Lesson> lessons, List<Session> sessions, List<Member> members) {
        while (true) {
            int counter = 1;
            ConsoleMessages.showSelectOptionMessage("");
            for (Lesson lesson : lessons) {
                System.out.println(counter + "- " + lesson.getLessonType().toString() + "     Price:" + lesson.getPrice());
                counter++;
            }
            ConsoleMessages.showBackOption();
            System.out.println("*** (Prices are per session.)");
            String selectedOption = scanner.nextLine();
            var lessonIndex = catchNumberFormatException(selectedOption, members.size());
            if (lessonIndex == -1) {
                BookByLessonMenu(lessons, sessions, members);
            }
            if (lessonIndex == 0) {
                bookClassByOptions();
            } else {
                Lesson selectedLesson = lessons.get(lessonIndex - 1);
                var selectedLessonSessions = sessionService.getSessionsByLesson(selectedLesson);
                while (true) {
                    System.out.println(selectedLesson.getLessonType().toString() + " sessions are as below");
                    ConsoleMessages.showSelectOptionMessage("");

                    counter = 1;
                    for (Session session : selectedLessonSessions) {
                        System.out.println(counter + "- " + session.getSessionDate().getFullDateString() + " -> " + session.getTimeSlot());
                        counter++;
                    }
                    ConsoleMessages.showBackOption();

                    selectedOption = scanner.nextLine();
                    var sessionIndex = catchNumberFormatException(selectedOption, members.size());
                    if (sessionIndex == -1||sessionIndex == 0) {
                        break;
                    }
                    else {
                        Session selectedSession = selectedLessonSessions.get(lessonIndex - 1);
                        ConsoleMessages.showSelectOptionMessage("");
                        System.out.println("(member you are booking for) \n");
                        for (Member m : members) {
                            System.out.println(members.indexOf(m)+1 + " - " + (m.toString()));
                        }
                        ConsoleMessages.showBackOption();

                        String memberIndexString = scanner.nextLine();
                        var memberIndex = catchNumberFormatException(memberIndexString, members.size());
                        if (memberIndex == -1||memberIndex == 0) {
                            break;
                        }
                        Member member = members.get(memberIndex);
                        bookingService.createBooking(member, selectedSession);
                        System.out.println("Booking has been created successfully \n");
                    }
                }
            }
        }
    }

    private int catchNumberFormatException(String memberIndex, int listSize) {
        int number = -1;
        try {
            number = Integer.parseInt(memberIndex);
            if (number < 0 || number > listSize) {
                ConsoleMessages.showWrongInputMessage("a number between options available");
                return -1;
            }
        } catch (NumberFormatException e) {
            ConsoleMessages.showWrongInputMessage("a number");
        }


        return number;
    }


}
