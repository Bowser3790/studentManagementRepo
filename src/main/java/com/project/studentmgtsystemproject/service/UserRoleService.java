package com.project.studentmgtsystemproject.service;


import com.project.studentmgtsystemproject.entity.concretes.UserRole;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.repository.UserRoleRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole (RoleType roleType){
        Optional<UserRole> userRole = userRoleRepository.findByEnumRoleEquals(roleType);

        // check the Optional usages in spring boot.

        if(userRole.isPresent()){
            return userRole.get();

        }else {
            throw new ConflictException(Messages.ROLE_NOT_FOUND);
        }
    }




}
