package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.EducationTerm;
import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import com.project.studentmgtsystemproject.entity.concretes.LessonProgram;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.request.LessonProgramRequest;
import com.project.studentmgtsystemproject.payload.response.LessonProgramResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.LessonProgramRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonProgramService {

    private final LessonProgramRepository lessonProgramRepository;

    private final LessonService lessonService;

    private final EducationTermService educationTermService;

    private final LessonProgramDto lessonProgramDto;


    public ResponseMessage<LessonProgramResponse> saveLessonProgram(LessonProgramRequest lessonProgramRequest){

        Set<Lesson> lessons = lessonService.getLessonByLessonIdSet(lessonProgramRequest.getLessonIdList());

        EducationTerm educationTerm = educationTermService.getEducationTermById(lessonProgramRequest.getEducationTermId());

        if(lessons.isEmpty()){
            throw new ResourceNotFoundException(Messages.NOT_FOUND_LESSON_IN_LIST);
        }else if(TimeControl.checkTime(lessonProgramRequest.getStartTime(), lessonProgramRequest.getStopTime())){
            throw new BadRequestException(Messages.TIME_NOT_VALID_MESSAGE);
        }

        LessonProgram lessonProgram = lessonProgramDto.mapLessonProgramRequestToLessonProgram(lessonProgramRequest,lessons,educationTerm);


        LessonProgram savedLessonProgram = lessonProgramRepository.save(lessonProgram);

        return ResponseMessage.<lessonProgramResponse>builder()
                .message("Lesson Program is Created!")
                .httpStatus(HttpStatus.CREATED)
                .object(lessonProgramDto.mapLessonProgramResponse(savedLessonProgram))
                .build();

    }


}
