package com.project.studentmgtsystemproject.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.studentmgtsystemproject.enums.Day;
import com.project.studentmgtsystemproject.payload.response.StudentResponse;
import com.project.studentmgtsystemproject.payload.response.TeacherResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LessonProgramRequest {


    private Set<TeacherResponse> teachers;
    private Set<StudentResponse> students;


    @NotNull(message = "Please enter day.")
    private Day day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    @NotNull(message = "Please enter start time.")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    @NotNull(message="Please enter end time")
    private LocalTime endTime;

    @NotNull(message = "Please select lesson")
    @Size(min=1, message = "Lesson must not be empty")
    @NotNull(message="Please enter education term")
    private Set<Long> lessonIdList;

    @NotNull(message="Please enter education term")
    private Long educationTermId;



}
