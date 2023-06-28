package com.project.studentmgtsystemproject.service;


import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.mapper.DeanDto;
import com.project.studentmgtsystemproject.payload.request.DeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.DeanRepository;
import com.project.studentmgtsystemproject.utils.CheckParameterUpdatedMethod;
import com.project.studentmgtsystemproject.utils.FieldControl;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeanService {
    private final FieldControl fieldControl;

    private final DeanDto deanDto;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final DeanRepository deanRepository;

    // TODO use mapstruct in your 3. repository

    public ResponseMessage<DeanResponse> save(DeanRequest deanRequest){
        fieldControl.checkDuplicate(deanRequest.getUsername(),deanRequest.getSsn(),deanRequest.getPhoneNumber());
        Dean dean = deanDto.mapDeanRequestToDean(deanRequest);
        dean.setUserRole(userRoleService.getUserRole(RoleType.MANAGER));
        dean.setPassword(passwordEncoder.encode(dean.getPassword()));

        Dean savedDean = deanRepository.save(dean);

        return ResponseMessage.<DeanResponse> builder()
                .message("Dean Saved")
                .httpStatus(HttpStatus.CREATED)
                .object(deanDto.mapDeanToDeanResponse(savedDean))
                .build();
    }

    // we are preventing the user to change the username + ssn + phone number
    public ResponseMessage<DeanResponse>update(DeanRequest deanRequest, Long deanId) {
        Optional<Dean> dean = isDeanExist(deanId);
        // do we really have a dean with this ID?
       if (!CheckParameterUpdatedMethod.checkUniqueProperties(dean.get(), deanRequest)) {
            fieldControl.checkDuplicate(deanRequest.getUsername(),
                                        deanRequest.getSsn(),
                                        deanRequest.getPhoneNumber());

        }
        Dean updatedDean =  deanDto.mapDeanRequestToUpdatedDean(deanRequest,deanId);
        updatedDean.setPassword(passwordEncoder.encode(deanRequest.getPassword()));
        Dean savedDean = deanRepository.save(updatedDean);

        return ResponseMessage.<DeanResponse>builder()
                .message("Dean Updated Successfully")
                .httpStatus(HttpStatus.OK)
                .object(deanDto.mapDeanToDeanResponse(savedDean))
                .build();

    }

    private Optional<Dean>isDeanExist(Long deanId) {
        Optional<Dean> dean = deanRepository.findById(deanId);
        if (!deanRepository.findById(deanId).isPresent()) {
            throw new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE, deanId));

        }else {
            return dean;
        }
    }

    public ResponseMessage<?>deleteDeanById(Long deanId){

        isDeanExist(deanId);
        deanRepository.deleteById(deanId);
        return ResponseMessage.builder()
                .message("Dean deleted")
                .httpStatus(HttpStatus.OK)
                .build();

    }

    private Dean createUpdateDean(DeanRequest deanRequest, Long managerId) {
        return Dean.builder()
                .id(managerId)
                .username(deanRequest.getUsername())
                .ssn(deanRequest.getSsn())
                .name(deanRequest.getName())
                .surname(deanRequest.getSurname())
                .birthPlace(deanRequest.getBirthPlace())
                .birthDay(deanRequest.getBirthDay())
                .phoneNumber(deanRequest.getPhoneNumber())
                .gender(deanRequest.getGender())
                .userRole(userRoleService.getUserRole(RoleType.MANAGER))
                .build();

    }



    public ResponseMessage<DeanResponse> getDeanById(Long deanId) {

        return ResponseMessage.<DeanResponse>builder()
                .message("Dean Deleted")
                .httpStatus(HttpStatus.OK)
                .object(deanDto.mapDeanToDeanResponse(isDeanExist(deanId).get()))
                .build();
    }

    public List<DeanResponse> getAllDeans() {

        return deanRepository.findAll()
                .stream()
                .map(deanDto::mapDeanToDeanResponse)
                .collect(Collectors.toList());

    }
    public Page<DeanResponse> getAllDeansByPage(int page,int size, String sort, String type){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());

        if(Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size,Sort.by(sort).descending());
        }
        return deanRepository.findAll(pageable).map(deanDto::mapDeanToDeanResponse);
    }
}
