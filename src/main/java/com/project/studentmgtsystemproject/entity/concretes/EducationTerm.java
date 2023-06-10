package com.project.studentmgtsystemproject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.studentmgtsystemproject.enums.Term;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EducationTerm {

    private Long id;


    @NotNull(message = "Education term must not be empty")
    @Enumerated(EnumType.STRING)
    private Term term;

    @NotNull(message = "Start date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "Start date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Start date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "last_registration_date")
    private LocalDate lastRegistrationDate;

//    @NotNull(message = "Start date must not be empty")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @Column(name = "start_date")
//    private LocalDate startDate;

}
