package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.abstracts.User;
import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.entity.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);

    Student findByUsernameEquals(String username);
}
