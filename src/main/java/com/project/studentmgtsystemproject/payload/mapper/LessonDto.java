package com.project.studentmgtsystemproject.payload.mapper;

import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import com.project.studentmgtsystemproject.entity.concretes.LessonProgram;
import com.project.studentmgtsystemproject.entity.concretes.ViceDean;
import com.project.studentmgtsystemproject.payload.request.LessonRequest;
import com.project.studentmgtsystemproject.payload.request.ViceDeanRequest;
import com.project.studentmgtsystemproject.payload.response.LessonResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LessonDto {

    public Lesson mapLessonRequestToLesson(LessonRequest lessonRequest) {
        return Lesson.builder()
                .lessonName(lessonRequest.getLessonName())
                .creditScore(lessonRequest.getCreditScore())
                .isCompulsory(lessonRequest.getIsCompulsory())
                .build();
    }
    public LessonResponse mapLessonToLessonResponse(Lesson lesson){

        return LessonResponse.builder()
                .lessonId(lesson.getLessonId())
                .lessonName(lesson.getLessonName())
                .creditScore(lesson.getCreditScore())
                .isCompulsory(lesson.getIsCompulsory())
                .build();
    }


}
