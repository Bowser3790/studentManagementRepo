package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.payload.request.LessonProgramRequest;
import com.project.studentmgtsystemproject.payload.response.LessonProgramResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.LessonProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/lessonPrograms")
@RequiredArgsConstructor
public class LessonProgramController {

    private final LessonProgramService lessonProgramService;
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage<LessonProgramResponse>saveLessonProgram(@RequestBody @Valid LessonProgramRequest lessonProgramRequest){

        return lessonProgramService.saveLessonProgram(lessonProgramRequest);

    }

}
