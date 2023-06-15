package com.project.studentmgtsystemproject;

import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentMgtSystemProjectApplication implements CommandLineRunner {

    private final UserRoleService userRoleService;

    public StudentMgtSystemProjectApplication(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
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

    }

}


