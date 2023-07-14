package com.project.studentmgtsystemproject.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.studentmgtsystemproject.entity.concretes.EducationTerm;
import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import com.project.studentmgtsystemproject.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonProgramResponse {

    private Long LessonProgramId;
    private Day day;
    private LocalDate startTime;
    private LocalDate stopTime;
    private Set<Lesson> lessonName;
    private EducationTerm educationTerm;
    private Set<TeacherResponse> teachers;
    private Set<StudentResponse> students;



}
