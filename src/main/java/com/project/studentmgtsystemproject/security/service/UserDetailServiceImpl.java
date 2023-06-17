package com.project.studentmgtsystemproject.security.service;

import com.project.studentmgtsystemproject.entity.concretes.*;
import com.project.studentmgtsystemproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor

public class UserDetailServiceImpl implements UserDetailsService {

    // we need to fetch user data from database and for that reason we have to make a dependency injection from AdminRepository
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final DeanRepository deanRepository;
    private final ViceDeanRepository viceDeanRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    // Transactional is very important: initializing the transactions and if you are trying to approach database to get the user information
    // no one will be able to do this because it will be locked.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsernameEquals(username);
        Teacher teacher = teacherRepository.findByUsernameEquals(username);
        Dean dean = deanRepository.findByUsernameEquals(username);
        ViceDean viceDean = viceDeanRepository.findByUsernameEquals(username);
        Student student = studentRepository.findByUsernameEquals(username);
        if (student != null) {
            return new UserDetailsImpl(
                    student.getId(),
                    student.getUsername(),
                    student.getName(),
                    false,
                    student.getPassword(),
                    student.getUserRole().getRoleType().name());
        }else if(teacher!=null){
            return new UserDetailsImpl(
                    teacher.getId(),
                    teacher.getUsername(),
                    teacher.getName(),
                    false,
                    teacher.getPassword(),
                    teacher.getUserRole().getRoleType().name());
            )else if(admin!=null){
                return new UserDetailsImpl(
                        admin.getId(),
                        admin.getUsername(),
                        admin.getName(),
                        false,
                        admin.getPassword(),
                        admin.getUserRole().getRoleType().name());
        }else if(dean!=null){
                return new UserDetailsImpl(
                        dean.getId(),
                        dean.getUsername(),
                        dean.getName(),
                        false,
                        dean.getPassword(),
                        dean.getUserRole().getRoleType().name());
        }else if(viceDean!=null){
                return new UserDetailsImpl(
                        viceDean.getId(),
                        viceDean.getUsername(),
                        viceDean.getName(),
                        false,
                        viceDean.getPassword(),
                        viceDean.getUserRole().getRoleType().name());
        }
        throw new UsernameNotFoundException("User '" + username + " ' not found");

        }
