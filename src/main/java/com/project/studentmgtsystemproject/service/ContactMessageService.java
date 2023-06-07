package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.ContactMessage;
import com.project.studentmgtsystemproject.payload.request.ContactMessageRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest){

        // it is expected to create one message in one day with the same email

        ContactMessage contactMessage = createContactMessage(contactMessageRequest);
        ContactMessage savedData = contactMessageRepository.save(contactMessage);
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message Created Successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(createResponse(savedData))
                .build();

        // we injected the contact message request from below into the parameter, NOW we get an Entity Type (ContactMessage)
        // we have a ContactMessage and we map it to our Entity Type, now we have an entity type, ready to save.

    }

    // This make ContactMessage maps to --> ContactMessageResponse
    private ContactMessageResponse createResponse(ContactMessage contactMessage){
        return ContactMessageResponse.builder()
                .name(contactMessage.getName())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .email(contactMessage.getEmail())
                .date(LocalDate.now())
                .build();
    }
    // Below) this makes ContactMessageRequest map to --> ContactMessage

    // this method will only be used in ContactMessageService Class so this will be a private method.
    // we are creating this method because the database only knows ContactMessage class it does not know
    // contactMessageRequest we need to link these two together we can go from service class to the repository to
    // the database.

    // a better name for this would be mapContactMessageRequestToContactMessage()
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
