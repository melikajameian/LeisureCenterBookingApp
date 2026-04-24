package domain.entities;

import domain.enums.DayOfWeek;
import domain.enums.Month;

public class SessionDate {
    private final Month month;
    private final int dayNumberInMonth;
    private final DayOfWeek dayOfWeek;

    public SessionDate(Month month, int dayNumberInMonth) {
        this.month = month;
        this.dayNumberInMonth = dayNumberInMonth;
        this.dayOfWeek = calculateDayOfWeek(dayNumberInMonth);
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

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getFullDateString(){
        return dayNumberInMonth + " " + month.monthName + " " + dayOfWeek.name().toLowerCase();
    }
}
