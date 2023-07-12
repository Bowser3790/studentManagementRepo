package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
