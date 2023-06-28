package com.project.studentmgtsystemproject.payload.mapper;

import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.entity.concretes.ViceDean;
import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.payload.request.DeanRequest;
import com.project.studentmgtsystemproject.payload.request.ViceDeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ViceDeanResponse;
import com.project.studentmgtsystemproject.service.UserRoleService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ViceDeanDto {

    private final UserRoleService userRoleService;

    public ViceDean mapViceDeanRequestToViceDean (ViceDeanRequest viceDeanRequest){
        return ViceDean.builder()
                .username(viceDeanRequest.getUsername())
                .name(viceDeanRequest.getName())
                .surname(viceDeanRequest.getSurname())
                .password(viceDeanRequest.getPassword())
                .ssn(viceDeanRequest.getSsn())
                .birthDay(viceDeanRequest.getBirthDay())
                .birthPlace(viceDeanRequest.getBirthPlace())
                .phoneNumber(viceDeanRequest.getPhoneNumber())
                .gender(viceDeanRequest.getGender())
                .build();
        
    };
    public ViceDeanResponse mapViceDeanToViceDeanResponse (ViceDean viceDean){
        return ViceDeanResponse.builder()
                .userId(viceDean.getId())
                .username(viceDean.getUsername())
                .name(viceDean.getName())
                .surname(viceDean.getSurname())
                .birthDay(viceDean.getBirthDay())
                .birthPlace(viceDean.getBirthPlace())
                .phoneNumber(viceDean.getPhoneNumber())
                .gender(viceDean.getGender())
                .ssn(viceDean.getSsn())
                .build();
    }
    public ViceDean mapDeanRequestToUpdatedViceDean(ViceDeanRequest viceDeanRequest, Long viceDeanId){
        return ViceDean.builder()
                .id(viceDeanId)
                .username(viceDeanRequest.getUsername())
                .ssn(viceDeanRequest.getSsn())
                .name(viceDeanRequest.getName())
                .surname(viceDeanRequest.getSurname())
                .birthPlace(viceDeanRequest.getBirthPlace())
                .birthDay(viceDeanRequest.getBirthDay())
                .phoneNumber(viceDeanRequest.getPhoneNumber())
                .gender(viceDeanRequest.getGender())
                .userRole(userRoleService.getUserRole(RoleType.ASSISTANT_MANAGER))
                .build();
    }

}
