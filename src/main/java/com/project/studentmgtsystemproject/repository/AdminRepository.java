package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    boolean existsByUsername(String username );

    boolean ssn(String ssn);

    boolean existsByPhoneNumber(String phone );

    Admin findByUsernameEquals(String username);

}
