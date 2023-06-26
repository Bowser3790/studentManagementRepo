package com.project.studentmgtsystemproject.controller;


import com.project.studentmgtsystemproject.payload.request.DeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dean")

public class DeanController {

    private final DeanService deanService;

    @PostMapping("/save")
    public ResponseMessage<DeanResponse> save(@RequestBody @Valid DeanRequest deanRequest){ // DeanRequest is DTO
        return deanService.save(deanRequest);
    }

    @PutMapping("/update/{userId}")
    public ResponseMessage<DeanResponse> update(@RequestBody @Valid DeanRequest deanRequest, @PathVariable Long userId){
        return deanService.update(deanRequest, userId);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseMessage<?> deleteDeanById(@PathVariable Long userId){

        return deanService.deleteDeanById(userId);

        // TODO Homework (write this delete message again with requests param
    }

}
