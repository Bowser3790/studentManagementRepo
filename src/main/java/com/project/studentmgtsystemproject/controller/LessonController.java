package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    public ResponseMessage<>
}
