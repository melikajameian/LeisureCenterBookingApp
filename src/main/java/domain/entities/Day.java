package domain.entities;

import domain.enums.DayOfWeek;

import java.util.Date;
import java.util.HashSet;

public class Day {
    Date date;
    DayOfWeek dayOfWeek;
    HashSet<Session> sessionList;
}
