package com.project.studentmgtsystemproject.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactMessageResponse implements Serializable {

    // note that this looks like an Entity class but this is a DTO class

    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDate date;


}

