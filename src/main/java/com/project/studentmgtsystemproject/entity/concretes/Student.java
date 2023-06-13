package com.project.studentmgtsystemproject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.studentmgtsystemproject.entity.abstracts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Student extends User {

    private String motherName;

    private String fatherName;

    private int studentNumber;

    private boolean isActive;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JsonIgnore
    private AdvisoryTeacher advisoryTeacher;

    @JsonIgnore
    @OneToMany(mappedBy="student", cascade = CascadeType.REMOVE)
    private List<StudentInfo> studentInfo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Student_lesson_program",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id)")
    )
    private Set<LessonProgram> lessonProgramList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "meet_student_table",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "meet_id")
    )
    private List<Meet> meetList;









}
