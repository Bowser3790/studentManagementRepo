package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.Admin;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.payload.request.AdminRequest;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.*;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final DeanRepository deanRepository;

    private final ViceDeanRepository viceDeanRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final GuestUserRepository guestUserRepository;



    public ResponseMessage save(AdminRequest adminRequest){
        checkDuplicate(adminRequest.getUsername(), adminRequest.getSsn(), adminRequest.getPhoneNumber());

        Admin admin = mapAdminRequestToAdmin(adminRequest);
        admin.setBuilt_in(false);

        // if username is also Admin we are setting build_in prop. to FALSE
        if(Objects.equals(adminRequest.getName(), "Admin")){
            admin.setBuilt_in(false);
        }
        admin.setUserRole();

    }
    private Admin mapAdminRequestToAdmin(AdminRequest adminRequest){
        return Admin.builder()
                .username(adminRequest.getUsername())
                .name(adminRequest.getName())
                .surname(adminRequest.getSurname())
                .password(adminRequest.getPassword())
                .ssn(adminRequest.getSsn())
                .birthDay(adminRequest.getBirthDay())
                .birthPlace(adminRequest.getBirthPlace())
                .phoneNumber(adminRequest.getPhoneNumber())
                .gender(adminRequest.getGender())
                .build();
    }

    // As a requirement all Admin, ViceAdmin, Dean, Student, Teacher,
    // should have unique username,email, ssn and phone number.
    public void checkDuplicate(String username, String ssn, String phone){

        if(adminRepository.existsByUsername(username) ||
                deanRepository.existsByUsername(username) ||
                studentRepository.existsByUsername(username) ||
                teacherRepository.existsByUsername(username) ||
                viceDeanRepository.existsByUsername(username) ||
                guestUserRepository.existsByUsername(username)){
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_USERNAME, username));
        }else if (adminRepository.existsBySsn(ssn) || 
                deanRepository.existsBySsn(ssn) ||
                studentRepository.existsBySsn(ssn) ||
                teacherRepository.existsBySsn(ssn) ||
                viceDeanRepository.existsBySsn(ssn) ||
                guestUserRepository.existsBySsn(ssn)){
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_SSN,ssn));
        } else if (adminRepository.existsByPhoneNumber(phone) ||
                deanRepository.existsByPhoneNumber(phone) ||
                studentRepository.existsByPhoneNumber(phone) ||
                teacherRepository.existsByPhoneNumber(phone) ||
                viceDeanRepository.existsByPhoneNumber(phone) ||
                guestUserRepository.existsByPhoneNumber(phone)) {
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_PHONE_NUMBER, phone));
            
        } else if (adminRepository.) {

        }


    }



}
