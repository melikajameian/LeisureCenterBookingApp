package domain.entities;

import domain.enums.DayOfWeek;

public class SessionDate {
    private final int month;
    private final int dayNumberInMonth;
    private final DayOfWeek dayOfWeek;

    public SessionDate(int month, int dayNumberInMonth) {
        this.month = validateMonth(month);
        this.dayNumberInMonth = dayNumberInMonth;
        this.dayOfWeek = calculateDayOfWeek(dayNumberInMonth);
    }

    private int validateMonth(int month){
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("Invalid month");
        }
        return month;
    }

    private DayOfWeek calculateDayOfWeek(int dayOfMonth) {
        if (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 15 || dayOfMonth == 22) {
            return DayOfWeek.SATURDAY;
        }
        if (dayOfMonth == 2 || dayOfMonth == 9 || dayOfMonth == 16 || dayOfMonth == 23) {
            return DayOfWeek.SUNDAY;
        }
        throw new IllegalArgumentException("Invalid coursework day: " + dayOfMonth);
    }

    public int getMonth() {
        return month;
    }

    public int getDayNumberInMonth() {
        return dayNumberInMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getFullDateString(){
        return dayNumberInMonth + "/" + month + "/" + "2026" + " " + dayOfWeek.name().toLowerCase();
    }
}
