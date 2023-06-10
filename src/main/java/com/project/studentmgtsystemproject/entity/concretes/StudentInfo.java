package com.project.studentmgtsystemproject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.studentmgtsystemproject.enums.Note;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class StudentInfo {

    private Integer absentee;
    private Double midtermExam;
    private Double finalExam;
    private Double examAverage;
    private String infoNote;

    @ManyToOne
    @JsonIgnoreProperties("teacher")
    private Teacher teacher;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private Note letterGrade;

    @OneToOne
    private EducationTerm educationTerm;





}
