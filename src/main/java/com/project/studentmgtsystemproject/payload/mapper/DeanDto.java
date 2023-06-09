package com.project.studentmgtsystemproject.payload.mapper;

import com.project.studentmgtsystemproject.entity.concretes.Dean;
import com.project.studentmgtsystemproject.payload.request.DeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class DeanDto {

    public Dean mapDeanRequestToDean (DeanRequest deanRequest){
        return Dean.builder()
                .username(deanRequest.getUsername())
                .name(deanRequest.getName())
                .surname(deanRequest.getSurname())
                .password(deanRequest.getPassword())
                .ssn(deanRequest.getSsn())
                .birthDay(deanRequest.getBirthDay())
                .birthPlace(deanRequest.getBirthPlace())
                .phoneNumber(deanRequest.getPhoneNumber())
                .gender(deanRequest.getGender())
                .build();

    }
    public DeanResponse mapDeanToDeanResponse (Dean dean){
        return DeanResponse.builder()
                .userId(dean.getId())
                .username(dean.getUsername())
                .name(dean.getName())
                .surname(dean.getSurname())
                .birthDay(dean.getBirthDay())
                .birthPlace(dean.getBirthPlace())
                .phoneNumber(dean.getPhoneNumber())
                .gender(dean.getGender())
                .ssn(dean.getSsn())
                .build();
    }

    public Dean mapDeanRequestToUpdatedDean(DeanRequest deanRequest, Long deanId) {
        return null;
    }
}
