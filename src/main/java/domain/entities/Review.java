package domain.entities;

import domain.enums.Rate;

public class Review {

    public Review(String description, Rate rate) {
        this.description = description;
        this.rate = rate;
    }

    private final String description;
    private final Rate rate;

    public String getDescription() {
        return description;
    }

    public Rate getRate() {
        return rate;
    }
}
