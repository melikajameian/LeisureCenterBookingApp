package presentation.console;

import application.services.LessonService;
import application.services.SessionService;
import domain.entities.Lesson;
import domain.entities.Session;
import domain.entities.SessionDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingMenu {
    private final Scanner scanner;
    private final SessionService sessionService;
    private final LessonService lessonService;

    public BookingMenu(SessionService sessionService, LessonService lessonService, Scanner scanner) {
        this.sessionService = sessionService;
        this.lessonService = lessonService;
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

        if (classByOptionInput.equals("1")) {
            BookByLessonMenu(lessons, sessions);
        } else if (classByOptionInput.equals("2")) {
            BookByDateMenu(sessions);
        } else {
            ConsoleMessages.showWrongInputMessage("1 or 2");
        }

    }

    private void BookByDateMenu(List<Session> sessions) {
        while(true){
        int counter = 1;

        for(Session session : sessions) {
            System.out.println(
                    counter + "- " +
                            session.getSessionDate().getFullDateString()+
                            " | " + session.getLesson().getLessonType().toString() +
                            " | " + session.getTimeSlot()
            );
            counter++;
        }
            int selectedOption ;
            String number = scanner.nextLine();
            try {
                selectedOption = Integer.parseInt(number);
                if (selectedOption < 0 || selectedOption > sessions.size()) {
                    ConsoleMessages.showWrongInputMessage("a number between options available");
                } else if (selectedOption == 0) {
                    bookClassByOptions();
                }else{
                    //implement
                }
            }catch (NumberFormatException e){
                ConsoleMessages.showWrongInputMessage("a number");

            }

        }
    }

    private void BookByLessonMenu(List<Lesson> lessons, List<Session> sessions) {
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
            try {
                int number = Integer.parseInt(selectedOption);
                if (number < 0 || number > lessons.size()) {
                    ConsoleMessages.showWrongInputMessage("a number between options available");
                } else if (number == 0) {
                    bookClassByOptions();
                }
                else {
                    Lesson selectedLesson = lessons.get(number - 1);
                    var selectedLessonSessions = new ArrayList<Session>();
                    for (Session session : sessions) {
                        if (session.getLesson().equals(selectedLesson))
                            selectedLessonSessions.add(session);
                    }
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
                        try {
                            number = Integer.parseInt(selectedOption);
                            if (number < 0 || number > selectedLessonSessions.size()) {
                                ConsoleMessages.showWrongInputMessage("a number between options available");

                            }else if (number == 0) {
                                break;
                            }else return;

                        } catch (NumberFormatException e) {
                            ConsoleMessages.showWrongInputMessage("a number");
                        }
                    }
                }

            } catch (NumberFormatException e) {
                ConsoleMessages.showWrongInputMessage("a number");
            }
        }
    }


}
