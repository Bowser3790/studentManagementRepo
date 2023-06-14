package com.project.studentmgtsystemproject.payload.request.abstracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.studentmgtsystemproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@SuperBuilder
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUserRequests {

    @NotNull(message = "Please enter your username")
    @Size(min = 4, max = 16,message="your name should be at least 4 chars")
    @Pattern(regexp = "\\(A(?!\\s*\\Z).+", message = "your surname must consist of the charecters.")
    private String username;

    @NotNull(message = "Please enter your name")
    @Size(min = 4, max = 16,message="your name should be at least 4 chars")
    @Pattern(regexp = "\\(A(?!\\s*\\Z).+", message = "your name must consist of the charecters.")
    private String name;

    @NotNull(message = "Please enter your surname")
    @Size(min = 4, max = 16,message="your name should be at least 4 chars")
    @Pattern(regexp = "\\(A(?!\\s*\\Z).+", message = "your surname must consist of the charecters.")
    private String surname;

    @NotNull(message = "Please enter your birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Your birthday cannot be in the future")
    private LocalDate birthDay;

    @NotNull
    @Pattern(regexp = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}(?!0000)[0-9]-{4}$",
            message = "Please enter a valid SSN number")
    private String ssn;

    @NotNull(message = "Please enter your birthPlace")
    @Size(min = 4, max = 16,message="your name should be at least 4 chars")
    @Pattern(regexp = "\\(A(?!\\s*\\Z).+", message = "your surname must consist of the characters.")
    private String birthPlace;

    @NotNull(message = "Please enter your username")
    @Size(min = 8, max = 60,message="your name should be at least 8 characters max 60 characters")
    private String password;

    @NotNull(message = "Please enter your phoneNumber")
    @Size(min = 12, max = 12,message="your phone number should be 12 characters")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{4}$",
            message = "please enter a valid phone number")
    private String phoneNumber;


    private Gender gender;




}
