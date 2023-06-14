package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.ViceDean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViceDeanRepository extends JpaRepository<ViceDean,Long> {

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phoneNumber);

    ViceDean findByUsernameEquals(String username);


}
