package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> { // Long => type of PrimaryKey.

    boolean existsByEmailEqualsAndDateEquals(String email, LocalDate date);
}
