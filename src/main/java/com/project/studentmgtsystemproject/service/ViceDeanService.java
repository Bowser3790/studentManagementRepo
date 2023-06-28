package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.entity.concretes.ViceDean;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.mapper.ViceDeanDto;
import com.project.studentmgtsystemproject.payload.request.ViceDeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.payload.response.ViceDeanResponse;
import com.project.studentmgtsystemproject.repository.ViceDeanRepository;
import com.project.studentmgtsystemproject.utils.CheckParameterUpdatedMethod;
import com.project.studentmgtsystemproject.utils.FieldControl;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ViceDeanService {

    private final ViceDeanRepository viceDeanRepository;

    public final FieldControl fieldControl;

    public final ViceDeanDto viceDeanDto;

    public final PasswordEncoder passwordEncoder;

    public final UserRoleService userRoleService;


    public ResponseMessage<ViceDeanResponse> saveViceDean(ViceDeanRequest viceDeanRequest) {
        fieldControl.checkDuplicate(viceDeanRequest.getUsername(),
                viceDeanRequest.getSsn(),
                viceDeanRequest.getPhoneNumber());
        ViceDean viceDean = viceDeanDto.mapViceDeanRequestToViceDean(viceDeanRequest);
        viceDean.setPassword(passwordEncoder.encode(viceDeanRequest.getPassword()));
        viceDean.setUserRole(userRoleService.getUserRole(RoleType.ASSISTANT_MANAGER));
        // why are we getting ASSISTANT_MANAGER, Should we be getting the ViceDean?

        ViceDean savedViceDean = viceDeanRepository.save(viceDean);

        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Saved")
                .httpStatus(HttpStatus.CREATED)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(savedViceDean))
                .build();


    }

    private Optional<ViceDean> isViceDeanExist(Long viceDeanId) {
        Optional<ViceDean> viceDean = viceDeanRepository.findById(viceDeanId);
        if (!viceDeanRepository.findById(viceDeanId).isPresent()) {
            throw new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE, viceDeanId));

        } else {
            return viceDean;
        }
    }

    public ResponseMessage<?> deleteViceDeanById(Long viceDeanId) {
        viceDeanRepository.deleteById(viceDeanId);
        return ResponseMessage.builder()
                .message("Vice Dean Deleted")
                .httpStatus(HttpStatus.OK)
                .build();

    }

    public ResponseMessage<ViceDeanResponse> getViceDeanById(Long viceDeanId) {
        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Deleted")
                .httpStatus(HttpStatus.OK)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(isViceDeanExist(viceDeanId).get()))
                .build();
    }
    public ResponseMessage<ViceDeanResponse> updateViceDean(ViceDeanRequest viceDeanRequest, Long viceDeanId){
        Optional<ViceDean> viceDean = isViceDeanExist(viceDeanId);
        if(!CheckParameterUpdatedMethod.checkUniqueProperties(viceDean.get(),viceDeanRequest)){
            fieldControl.checkDuplicate(viceDeanRequest.getUsername(),
                                        viceDeanRequest.getSsn(),
                                        viceDeanRequest.getPhoneNumber());

        }
        ViceDean updatedViceDean = viceDeanDto.mapDeanRequestToUpdatedViceDean(viceDeanRequest, viceDeanId);
        updatedViceDean.setPassword(passwordEncoder.encode(viceDeanRequest.getPassword()));
        ViceDean savedViceDean = viceDeanRepository.save(updatedViceDean);

        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Updated")
                .httpStatus(HttpStatus.OK)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(savedViceDean))
                .build();
    }

}
