package com.project.studentmgtsystemproject;

import com.project.studentmgtsystemproject.enums.Gender;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.payload.request.AdminRequest;
import com.project.studentmgtsystemproject.service.AdminService;
import com.project.studentmgtsystemproject.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class StudentMgtSystemProjectApplication implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final AdminService adminService;

    public StudentMgtSystemProjectApplication(UserRoleService userRoleService, AdminService adminService) {
        this.userRoleService = userRoleService;
        this.adminService = adminService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentMgtSystemProjectApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception{
        if(userRoleService.getAllUserRole().isEmpty()){
            userRoleService.save(RoleType.ADMIN);
            userRoleService.save(RoleType.MANAGER);
            userRoleService.save(RoleType.ASSISTANT_MANAGER);
            userRoleService.save(RoleType.TEACHER);
            userRoleService.save(RoleType.STUDENT);
            userRoleService.save(RoleType.ADVISORY_TEACHER);
            userRoleService.save(RoleType.GUEST_USER);
        }



        if(adminService.countAllAdmins()==0){
            AdminRequest adminRequest = new AdminRequest();
            adminRequest.setUsername("Admin");
            adminRequest.setSsn("655-44-5689");
            adminRequest.setPassword("AnkerMan*89");
            adminRequest.setName("Lara");
            adminRequest.setSurname("Bond");
            adminRequest.setPhoneNumber("555-444-6588");
            adminRequest.setGender(Gender.FEMALE);
            adminRequest.setBirthDay(LocalDate.of(1980,2,2));
            adminRequest.setBirthPlace("Texas");
            adminService.save(adminRequest);
        }

    }

}


