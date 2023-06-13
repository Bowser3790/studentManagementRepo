package com.project.studentmgtsystemproject.entity.abstracts;

// abstract means you cannot create an object from the class

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.studentmgtsystemproject.entity.concretes.UserRole;
import com.project.studentmgtsystemproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

/*
Notes this class is a super class and is not an entity class ... we will be adding entity classes to the and stating their
relationship.  But this is an abstract class where we are creating common values for all different types of users.
- child entity classes will extend this class and we will be able to use properties of this class as well.
This is where we started by creating this abstract class.
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class User { // if you click on the blue button on line 28 it will show you the classes that are built in
    // the built in functionality you will be able to see in the admin class built_in is declared;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String ssn;

    private String name;

    private String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String birthPlace;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // meaning you cannot make a SQL query to get this value to keep private.
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserRole userRole;

    private Gender gender; // enums we do not need to put any relationship type OneToOne etc...






}
