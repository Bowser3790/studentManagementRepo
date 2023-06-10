package com.project.studentmgtsystemproject.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;

    @JsonFormat(shape = JsonFormat.shape.STRING, pattern = "HH:mm",timezone = "US")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.shape.STRING, pattern = "HH:mm",timezone = "US")
    private LocalTime stopTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("teacher")
    private AdvisoryTeacher advisoryTeacher;

    @ManyToMany
    @JoinTable(
            name = "meet_student_table",
            joinColumns =
    )

}
