package com.rafaelswr.springsecurityindeep.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fname;
    private String lname;

    private String creditCardNumber;

    @OneToOne(mappedBy = "simpleUser")
    @JsonBackReference
    private AuthUser authUser;


    public SimpleUser(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }
}
