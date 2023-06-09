package com.project.studentmgtsystemproject.entity.concretes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long lessonId;
    private String lessonName;
    private Integer creditScore;
    private Boolean isCompulsory;

}
