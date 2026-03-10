package domain.entities;

import java.util.UUID;

public class Member {
    String id;
    String firstName;
    String lastName;

    public Member( String firstName,String lastName) {
        this.id =  UUID.randomUUID().toString();
        this.lastName = lastName;
        this.firstName = firstName;

    }


}
