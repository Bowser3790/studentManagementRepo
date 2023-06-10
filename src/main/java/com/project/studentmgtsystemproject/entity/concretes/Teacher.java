package com.project.studentmgtsystemproject.entity.concretes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true)
public class Teacher {

    //TODO learn about cascade types and orphanRemoval
    OneToOne(mappedBy = "teacher", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private AdvisoryTeacher advisoryTeacher;

    @Column(name = "isAdvisor")
    private boolean isAdvisor;

    @Column(unique = true)
    private String email;

    @OneToMany
    @JoinTable(
            name = "teacher_lessonProgram",
            joinColumns = @JoinColumn(name= )
    )
}
