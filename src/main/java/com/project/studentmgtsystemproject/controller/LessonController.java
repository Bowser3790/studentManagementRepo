package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import com.project.studentmgtsystemproject.payload.request.LessonRequest;
import com.project.studentmgtsystemproject.payload.response.LessonResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'ASSISTANT_MANAGER')")
    @PostMapping("/save")
    public ResponseMessage<LessonResponse> saveLesson(@RequestBody @Valid LessonRequest lessonRequest){
        return lessonService.saveLesson(lessonRequest);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'ASSISTANT_MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseMessage deleteLesson(@PathVariable Long id){
        return lessonService.deleteLessonById(id);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'ASSISTANT_MANAGER')")
    @GetMapping("/getLessonByName")
    public ResponseMessage getLessonByLessonName(@RequestParam String lessonName){
        return lessonService.getLessonByLessonName(lessonName);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'ASSISTANT_MANAGER')")
    @GetMapping("/search")
    public Page<LessonResponse> searchLesson(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "type") String type){
        return lessonService.findLessonByPage(page,size,sort,type);

    }

    @GetMapping("/getAllLessonByLessonId")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'ASSISTANT_MANAGER')")
    public Set<Lesson> getAllLessonByLessonId(@RequestParam(name = "lessonId") Set<Long>idSet){
        return lessonService.getLessonByLessonIdSet(idSet);

    }

    //TODO please make an update end-point

}
