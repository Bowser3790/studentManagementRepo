package com.project.studentmgtsystemproject.repository;

import com.project.studentmgtsystemproject.entity.concretes.UserRole;
import com.project.studentmgtsystemproject.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


    @Query("SELECT r FROM UserRole r WHERE r.roleType=?1")
    Optional<UserRole> findByEnumRole(RoleType roleType);


    @Query("SELECT count(r) > 0 FROM UserRole r WHERE r.roleType = ?1")
    boolean existByEnumRoleEquals(RoleType roleType);

    Optional<UserRole> findByEnumRoleEquals(RoleType roleType);
}
