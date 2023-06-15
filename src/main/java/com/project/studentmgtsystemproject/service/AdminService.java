package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.Admin;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.payload.request.AdminRequest;
import com.project.studentmgtsystemproject.payload.response.AdminResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.*;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.print.Pageable;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final DeanRepository deanRepository;

    private final ViceDeanRepository viceDeanRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final GuestUserRepository guestUserRepository;

    private final UserRoleService userRoleService;



    public ResponseMessage save(AdminRequest adminRequest){
        checkDuplicate(adminRequest.getUsername(), adminRequest.getSsn(), adminRequest.getPhoneNumber());

        Admin admin = mapAdminRequestToAdmin(adminRequest);
        admin.setBuilt_in(false);

        // if username is also Admin we are setting build_in prop. to FALSE
        if(Objects.equals(adminRequest.getName(), "Admin")){
            admin.setBuilt_in(false);
        }
        admin.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));

        // we will implement password encoder here.
        Admin savedAdmin = adminRepository.save(admin);

        //In response message savedAdmin instance may not be sent back to the front-end.
        return ResponseMessage.<AdminResponse>builder()
                .message("Admin Saved") // message saved will be sent to the front end
                .httpStatus(HttpStatus.CREATED) // if the code ends at this line we will send a response to the front end with the status code for created// probably 200 code.
                .object(mapAdminToAdminResponse(savedAdmin))// this line is not necessary this saves the information to the front end
                .build();

    }
    public Page<Admin> getAllAdmins(Pageable pageable){
        return adminRepository.findAll(pageable);

    }
    public String deleteAdmin(Long id){
        //we should check the database if the ID exists
        Optional<Admin>admin = adminRepository.findById(id);

    }
    private AdminResponse mapAdminToAdminResponse(Admin admin){
        return AdminResponse.builder()
                .userId(admin.getId())
                .username(admin.getUsername())
                .name(admin.getName())
                .surname(admin.getSurname())
                .phoneNumber(admin.getPhoneNumber())
                .gender(admin.getGender())
                .ssn(admin.getSsn())
                .build();
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
            
        }

        }

    public long countAllAdmins(){
        return adminRepository.count();
    }

    }

