package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.payload.request.ContactMessageRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.ContactMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contactMessages")
@RequiredArgsConstructor

public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping ("/save") // "save"
    public ResponseMessage<ContactMessageResponse>save(@RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.save(contactMessageRequest);
    }


}
