package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.mapper.LessonDto;
import com.project.studentmgtsystemproject.payload.request.LessonRequest;
import com.project.studentmgtsystemproject.payload.response.LessonResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.LessonRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import com.project.studentmgtsystemproject.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonDto lessonDto;
    private final LessonRepository lessonRepository;
    private final ServiceHelpers serviceHelpers;


    public ResponseMessage<LessonResponse> saveLesson(LessonRequest lessonRequest) {
        isLessonExistsByLessonName(lessonRequest.getLessonName());

        Lesson savedLesson = lessonRepository.save(lessonDto.mapLessonRequestToLesson(lessonRequest));

        return ResponseMessage.<LessonResponse>builder()
                .object(lessonDto.mapLessonToLessonResponse(savedLesson))
                .message("Lesson Created Successfully")
                .httpStatus(HttpStatus.CREATED)
                .build();


    }

    public ResponseMessage deleteLessonById(Long id) {
        isLessonExistsById(id);
        lessonRepository.deleteById(id);

        return ResponseMessage.builder()
                .message("Lesson is deleted successfully")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    public ResponseMessage<LessonResponse>getLessonByLessonName(String lessonName){
        return ResponseMessage.<LessonResponse>builder()
                .message("Lesson is successfully found")
                .object(lessonDto.mapLessonToLessonResponse(lessonRepository.getLessonByLessonName(lessonName).get()))
                .build();



    }
    public Page<LessonResponse> search(int page, int size, String sort, String type){

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);
        return lessonRepository.findAll(pageable).map(lessonDto::mapLessonToLessonResponse);
    }

    // TODO incase of returning empty collection, exception handling may be demanded.
    public Set<Lesson> getLessonByLessonIdSet(Set<Long> lessons){
        return lessonRepository.getLessonByLessonIdList(lessons);
        // this should have been mapped through DTO object.
    }



    private boolean isLessonExistsByLessonName(String lessonName) {

        boolean lessonExist = lessonRepository.existsLessonByLessonNameEqualsIgnoreCase(lessonName);
        if (lessonExist) {
            throw new ConflictException(String.format(Messages.ALREADY_REGISTERED_LESSON_MESSAGE, lessonName));
        } else {
            return false;
        }
    }
    private void isLessonExistsById(Long id) {
        lessonRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException(String.format(Messages.LESSON_MESSAGE_NOT_FOUND,id));});
        }

    public Page<LessonResponse> findLessonByPage(int page, int size, String sort, String type) {
    }
}

