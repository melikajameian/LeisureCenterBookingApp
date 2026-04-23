package domain.enums;

public enum Month {
    JANUARY(1,"January"),
    FEBRUARY(2,"February"),
    MARCH(3,"March"),
    APRIL(4,"April"),
    MAY(5,"May"),
    JUNE(6,"June"),
    JULY(7,"July"),
    AUGUST(8,"August"),
    SEPTEMBER(9,"September"),
    OCTOBER(10,"October"),
    NOVEMBER(11,"November"),
    DECEMBER(12,"December");

    Month(int monthNumber, String monthName) {
        this.monthNumber = monthNumber;
        this.monthName = monthName;
    }

    public final int monthNumber;
    public final String monthName;


    public static Month getRateByNumber(int number){
        for (Month m : Month.values()) {
            if (m.monthNumber == number) {
                return m;
            }
        }
        return null;
    }
}
