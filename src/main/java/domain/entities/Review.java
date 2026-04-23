package domain.entities;

import domain.enums.Rate;

public class Review {

    public Review(String description, Rate rate) {
        this.description = description;
        this.rate = rate;
    }

    private String description;
    private Rate rate;
}
