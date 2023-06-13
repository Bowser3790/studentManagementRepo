package com.project.studentmgtsystemproject.entity.concretes;

import javax.persistence.*;

import com.project.studentmgtsystemproject.entity.abstracts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Teacher extends User {

    //TODO learn about cascade types and orphanRemoval... bottom of this link is more info https://chat.openai.com/share/68bba05a-415d-431c-9564-9f1902a74300
    // To define how changes to the owner entity should propagate to the associated entities.
    // Learn About Database views https://chat.openai.com/share/68bba05a-415d-431c-9564-9f1902a74300


    @OneToOne(mappedBy = "teacher", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private AdvisoryTeacher advisoryTeacher;

    @Column(name = "isAdvisor")
    private boolean isAdvisor;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.REMOVE)
    private List<StudentInfo> studentInfo;




    @ManyToMany
    @JoinTable(
            name = "teacher_lesson_program",
            joinColumns = @JoinColumn(name= "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id")
    )
    private Set<LessonProgram> lessonProgramList;
}


// In this example, the customer_orders view is created by joining the customers and orders tables based on the
// customer_id column. The view retrieves and presents the customer name, order number, and order date. Once the
// view is created, it can be queried just like any other table, providing a convenient way to access the combined
// data.
//
//Views are a powerful feature of databases that enable data abstraction, security, performance optimization, and
// logical independence, contributing to efficient data management and application development.