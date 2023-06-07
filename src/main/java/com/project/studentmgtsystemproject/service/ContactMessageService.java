package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.ContactMessage;
import com.project.studentmgtsystemproject.payload.request.ContactMessageRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest){

        ContactMessage contactMessage = createContactMessage(contactMessageRequest);
        // we injected the contact message request from below into the parameter, NOW we get an Entity Type (ContactMessage)
        // we have a ContactMessage and we map it to our Entity Type and now we have an entity type and its ready to save.
        return null;
    }
    // The way this workds is:
    // we have our

    // this method will only be used in ContactMessageService Class so this will be a private method.
    // we are creating this method because the database only knows ContactMessage class it does not know
    // contactMessageRequest we need to link these two together so we can go from service class to the repository to
    // the database.
    private ContactMessage createContactMessage(ContactMessageRequest contactMessageRequest){
        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage())
                .email(contactMessageRequest.getEmail())
                .date(LocalDate.now())
                .build();
        // note that the builder design structure has a constructor for each of the variables from the ContactMessage
        // class we created.  This makes your code easier to manage and construct. If I want a method that has only name
        // and email all i have to do is call the builder method from above and create a method with just the variables I need.

    }



}
