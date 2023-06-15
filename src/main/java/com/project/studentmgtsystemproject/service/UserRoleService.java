package com.project.studentmgtsystemproject.service;


import com.project.studentmgtsystemproject.entity.concretes.UserRole;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ConflictException;
import com.project.studentmgtsystemproject.repository.UserRoleRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole (RoleType roleType){
//        Optional<UserRole> userRole = userRoleRepository.findByEnumRoleEquals(roleType);

        // check the Optional usages in spring boot.

//        if(userRole.isPresent()){
//            return userRole.get();
//
//        }else {
//            throw new ConflictException(Messages.ROLE_NOT_FOUND);
//        }
    return userRoleRepository.findByEnumRoleEquals(roleType).orElseThrow(
            ()-> new ConflictException(Messages.ROLE_NOT_FOUND));

    }
    public List<UserRole> getAllUserRole(){
        return userRoleRepository.findAll();

    }
    public UserRole save(RoleType roleType){
        if(userRoleRepository.existByEnumRoleEquals(roleType)){
            throw new ConflictException(Messages.ROLE_ALREADY_EXISTS_IN_DATABASE);
        }
        UserRole userRole = UserRole.builder().roleType(roleType).build();
        return userRoleRepository.save(userRole);

    }




}
