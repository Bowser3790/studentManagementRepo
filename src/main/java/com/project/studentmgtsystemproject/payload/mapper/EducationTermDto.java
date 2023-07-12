package com.project.studentmgtsystemproject.payload.mapper;

import com.project.studentmgtsystemproject.entity.concretes.EducationTerm;
import com.project.studentmgtsystemproject.payload.request.EducationTermRequest;
import com.project.studentmgtsystemproject.payload.response.EducationTermResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EducationTermDto{
    public EducationTerm mapEducationTermRequestToEducationTerm(EducationTermRequest educationTermRequest){
        return EducationTerm.builder()
                .term(educationTermRequest.getTerm())
                .startDate(educationTermRequest.getStartDate())
                .endDate(educationTermRequest.getEndDate())
                .lastRegistrationDate(educationTermRequest.getLastRegistrationDate())
                .build();
    }
    public EducationTerm mapEducationTermRequestToUpdatedEducationTerm(Long id, EducationTermRequest educationTermRequest){
        return mapEducationTermRequestToEducationTerm(educationTermRequest)
                .toBuilder()
                .id(id)
                .build();



               // this would be the explicit way to code this part as we did above new return method we are using will be more difficult than the version below.
//                EducationTerm.builder()
//                .id(id)
//                .term(educationTermRequest.getTerm())
//                .startDate(educationTermRequest.getStartDate())
//                .endDate(educationTermRequest.getEndDate())
//                .lastRegistrationDate(educationTermRequest.getLastRegistrationDate())
//                .build();
    }
    public EducationTermResponse mapEducationTermToEducationTermResponse(EducationTerm educationTerm){
        return EducationTermResponse.builder()
                .id(educationTerm.getId())
                .term(educationTerm.getTerm())
                .startDate(educationTerm.getStartDate())
                .endDate(educationTerm.getEndDate())
                .lastRegistrationDate(educationTerm.getLastRegistrationDate())
                .build();
    }

}