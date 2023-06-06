package com.project.studentmgtsystemproject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

// TODO check all generation types and strategies -> this is for job interviews
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

// TODO learn about serialization and deserialization

public class ContactMessage implements Serializable {

    @Id
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String subject;
    @NotNull
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // 2025-06-05
    private LocalDate date;



}
