package domain.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
    private final String id;
    private  String firstName;
    private  String lastName;
    private ArrayList<Booking> bookings;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}
