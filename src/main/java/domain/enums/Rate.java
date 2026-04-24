package domain.enums;

public enum Rate {
    very_dissatisfied("Very dissatisfied", 1),
    dissatisfied("Dissatisfied", 2),
    ok("ok", 3),
    satisfied("Satisfied", 4),
    very_satisfied("Very satisfied", 5);


    final String label;
    final int rate;

    Rate(String label, int rate) {
        this.label = label;
        this.rate = rate;
    }

    public int getRateNumber() {
        return rate;
    }

    public static Rate getRateByNumber(int number) {
        for (Rate r : Rate.values()) {
            if (r.rate == number) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "       ⤷ Rate ~> " +
                rate +
                "/5" +
                "(" + label + ")";
    }
}
