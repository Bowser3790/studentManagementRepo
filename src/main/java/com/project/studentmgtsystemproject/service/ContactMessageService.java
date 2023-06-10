package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.ContactMessage;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.payload.request.ContactMessageRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.ContactMessageRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest){

        // Overview of what is happening in this class: https://chat.openai.com/share/e2f42bb6-844f-47f9-8809-3d57603b86ea


        // it is expected to create one message in one day with the same email
        boolean isSameMessageWithSameEmailForToday =
                contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.getEmail(), LocalDate.now());

        if(isSameMessageWithSameEmailForToday){
            throw new ConflictException(Messages.ALREADY_SEND_A_MESSAGE_TODAY);
        }
        // create a ContactMessage object from the ContactMessageRequest
        ContactMessage contactMessage = createContactMessage(contactMessageRequest);
        // save the ContactMessage to the database using the repository
        ContactMessage savedData = contactMessageRepository.save(contactMessage);
        // for the response object 2 options: 1 with the object created below .object(createResponse(savedData))
        // or create an object
        //ContactMessageResponse response = createResponse(savedData); call this object => (below) .object(response)
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message Created Successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(createResponse(savedData))// create a contactMessageResponse object from the saved contactMessage
                .build();
        // we injected the contact message request from below into the parameter, NOW we get an Entity Type (ContactMessage)
        // we have a ContactMessage and we map it to our Entity Type, now we have an entity type, ready to save.

    }

    public Page<ContactMessageResponse> getAll(int page,int size, String sort, String type){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if(Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size,Sort.by(sort).descending());
            // to query the database for this information for Response message we have to connect to the ContactMessageRepository.
        }
        return contactMessageRepository.findAll(pageable).map(this::createResponse);
        // https://chat.openai.com/share/8cc616da-eb74-430b-94e1-a1a0259c2d1a scroll to the bottom of this link for a
        // high level overview of this method

        // 1) this return message need to find the repository.  2) find All Methods 3) from pageable
        /*
        Overall, the Page<ContactMessageResponse> serves as a container that holds the retrieved data from the database,
        and it acts as a means to transfer that data from the server to the client.
         */
    }
    public Page<ContactMessageResponse>searchByEmail(String email, int page, int size, String sort, String type){
        Pageable pageable = PageRequest.of(page,size,Sort.by(sort).ascending());
                if(Objects.equals(type,"desc")){
                    pageable = PageRequest.of(page,size,Sort.by(sort).descending());
                }
                return contactMessageRepository.findByEmailEquals(email,pageable).map(this::createResponse);
    }
    // if you are having trouble understanding the return statement above go to this link: https://chat.openai.com/share/971b980c-65c0-4fd6-8537-355771794ba1
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


    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if(Objects.equals(type,"desc")){
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return contactMessageRepository.findBySubjectEquals(subject,pageable).map(this::createResponse);
    }

    public void deleteContactMessageById(Long id) {
    }
}
