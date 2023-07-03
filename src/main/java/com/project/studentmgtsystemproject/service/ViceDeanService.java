package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.ViceDean;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.mapper.ViceDeanDto;
import com.project.studentmgtsystemproject.payload.request.ViceDeanRequest;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.payload.response.ViceDeanResponse;
import com.project.studentmgtsystemproject.repository.ViceDeanRepository;
import com.project.studentmgtsystemproject.utils.CheckParameterUpdatedMethod;
import com.project.studentmgtsystemproject.utils.ServiceHelpers;
import com.project.studentmgtsystemproject.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ViceDeanService {

    private final ViceDeanRepository viceDeanRepository;

    public final ServiceHelpers serviceHelpers;

    public final ViceDeanDto viceDeanDto;

    public final PasswordEncoder passwordEncoder;

    public final UserRoleService userRoleService;


    public ResponseMessage<ViceDeanResponse> saveViceDean(ViceDeanRequest viceDeanRequest) {
        serviceHelpers.checkDuplicate(viceDeanRequest.getUsername(),
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
            serviceHelpers.checkDuplicate(viceDeanRequest.getUsername(),
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
    public List<ViceDeanResponse> getAllViceDeans(){
        return viceDeanRepository.findAll()
                .stream()
                .map(viceDeanDto::mapViceDeanToViceDeanResponse)
                .collect(Collectors.toList());

    }
    public Page<ViceDeanResponse> getAllViceDeansByPage(int page, int size, String sort, String type) {

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return viceDeanRepository.findAll(pageable).map(viceDeanDto::mapViceDeanToViceDeanResponse);
    }

}
