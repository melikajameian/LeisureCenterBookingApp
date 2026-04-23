package domain.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
    private final String id;
    private final String firstName;
    private final String lastName;

    public Member( String firstName,String lastName) {
        this.id =  UUID.randomUUID().toString();
        this.lastName = lastName;
        this.firstName = firstName;

    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
