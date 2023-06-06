package com.project.studentmgtsystemproject.entity.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

// TODO check all generation types and strategies -> this is for job interviews
@Entity

public class ContactMessage {

    @Id
    private int id;

    private String name;

    private String email;

    private String subject;

    private String message;

    private LocalDate date;



}
