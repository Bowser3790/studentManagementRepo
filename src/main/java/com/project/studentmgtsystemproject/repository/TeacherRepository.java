package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.entity.concretes.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);

    Teacher findByUsernameEquals(String username);
}
