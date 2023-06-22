package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.abstracts.User;
import com.project.studentmgtsystemproject.entity.concretes.Dean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeanRepository extends JpaRepository<Dean,Long>{

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);

    Dean findByUsernameEquals(String username);




}