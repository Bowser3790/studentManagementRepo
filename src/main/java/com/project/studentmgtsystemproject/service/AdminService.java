package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.Admin;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.payload.request.AdminRequest;
import com.project.studentmgtsystemproject.payload.response.AdminResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.*;
import com.project.studentmgtsystemproject.utils.ServiceHelpers;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final UserRoleService userRoleService;

    private final ServiceHelpers serviceHelpers;



    public ResponseMessage save(AdminRequest adminRequest){
        serviceHelpers.checkDuplicate(adminRequest.getUsername(), adminRequest.getSsn(), adminRequest.getPhoneNumber());

        Admin admin = mapAdminRequestToAdmin(adminRequest);
        admin.setBuilt_in(false);

        // if username is also Admin we are setting build_in prop. to FALSE
        if(Objects.equals(adminRequest.getName(), "Admin")){
            admin.setBuilt_in(true);
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
        return adminRepository.findAll((org.springframework.data.domain.Pageable) pageable);

    }
    public String deleteAdmin(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();

            if (admin.isBuilt_in()) {
                throw new ConflictException(Messages.NOT_PERMITTED_METHOD_MESSAGE);
            } else {
                adminRepository.deleteById(id);
                return Messages.ADMIN_DELETED_SUCCESSFULLY;
            }
        } else {
            throw new ConflictException(String.format(Messages.NOT_FOUND_USER_MESSAGE, id));
        }
    }
//    public String deleteAdmin(Long id){
//        //we should check the database if the ID exists
//        Optional<Admin>admin = adminRepository.findById(id);
//
//        // TO DO please divide the cases and throw meaningful response messages
//        if(admin.isPresent() && admin.get().isBuilt_in()) {
//            throw new ConflictException(Messages.NOT_PERMITTED_METHOD_MESSAGE);
//        }
//        if(admin.isPresent()) {
//            adminRepository.deleteById(id);
//            // TODO please divide the cases and throw meaningful response message
//            return "Admin deleted successfully";
//        }
//        return String.format(Messages.NOT_FOUND_USER_MESSAGE)
//
//    }


    // TO DO: move this to mappers package and create an admin class
    // make the same implementation as we did in DeanDto, and ViceDeanDto classes
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

    public long countAllAdmins(){
        return adminRepository.count();
    }

    }

