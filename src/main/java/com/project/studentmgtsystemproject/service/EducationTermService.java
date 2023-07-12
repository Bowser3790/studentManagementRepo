package com.project.studentmgtsystemproject.service;

import com.project.studentmgtsystemproject.entity.concretes.EducationTerm;
import com.project.studentmgtsystemproject.exception.ResourceNotFoundException;
import com.project.studentmgtsystemproject.payload.mapper.EducationTermDto;
import com.project.studentmgtsystemproject.payload.request.EducationTermRequest;
import com.project.studentmgtsystemproject.payload.response.EducationTermResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.repository.EducationTermRepository;
import com.project.studentmgtsystemproject.utils.Messages;
import com.project.studentmgtsystemproject.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationTermService {

    private final EducationTermRepository educationTermRepository;
    private final EducationTermDto educationTermDto;
    private final ServiceHelpers serviceHelpers;


    public ResponseMessage<EducationTermResponse> saveEducationTerm(EducationTermRequest educationTermRequest){

        checkEducationTermDate(educationTermRequest);

        EducationTerm savedEducationTerm = educationTermRepository.save(educationTermDto.mapEducationTermRequestToEducationTerm(educationTermRequest));

        return ResponseMessage.<EducationTermResponse>builder()
                .message("Education term saved")
                .object(educationTermDto.mapEducationTermToEducationTermResponse(savedEducationTerm))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    public EducationTermResponse getEducationTermById(Long id){
         //return educationTermDto.mapEducationTermToEducationTermResponse(educationTermRepository.findById(id))

        EducationTerm term = educationTermRepository.findByIdEquals(id);

        if (term == null){
            throw new ResourceNotFoundException(String.format(Messages.EDUCATION_TERM_NOT_FOUND, id));
        }else{
            return educationTermDto.mapEducationTermToEducationTermResponse(term);
        }
    }
    public ResponseMessage<?>deleteEducationTermById(Long id){
        checkEducationTermExists(id);
        educationTermRepository.deleteById(id);
        return ResponseMessage.builder()
                .message("Education Term Deleted Successfully")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    public Page<EducationTermResponse> getAllEducationTermsByPage(int page, int size, String sort, String type){
        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);
        return educationTermRepository.findAll(pageable).map(educationTermDto::mapEducationTermToEducationTermResponse);

    }
    public ResponseMessage<EducationTermResponse>updateEducationTerm(Long id, EducationTermRequest educationTermRequest){
        checkEducationTermExists(id);
        checkEducationTermDate(educationTermRequest);
        EducationTerm educationTermUpdated = educationTermRepository.save(educationTermDto.mapEducationTermRequestToUpdatedEducationTerm(id,educationTermRequest));

        return ResponseMessage.<EducationTermResponse>builder()
                .message("Education Term Updated")
                .httpStatus(HttpStatus.OK)
                .object(educationTermDto.mapEducationTermToEducationTermResponse(educationTermUpdated))
                .build();
    }

    private EducationTerm checkEducationTermExists(Long id){
        EducationTerm term = educationTermRepository.findByIdEquals(id){
            if(term==null){
                throw new ResourceNotFoundException(String.format(Messages.NOT_FOUND_EDUCATION_TERM_BY_ID, id));
            }else{
                return term;
            }
        }

    }


    private void checkEducationTermDate(EducationTermRequest educationTermRequest){

        // TODO another requirement can be needed for validating => registration -> end
        // check the dates also for TODAY


        // RegistrationDate > StartDate
        if (educationTermRequest.getLastRegistrationDate().isAfter(educationTermRequest.getStartDate())){
            throw new ResourceNotFoundException(Messages.EDUCATION_START_DATE_IS_EARLIER_THAN_LAST_REGISTRATION_DATE);
        }

        // endDate > startDate
        if (educationTermRequest.getEndDate().isBefore(educationTermRequest.getStartDate())){
            throw new ResourceNotFoundException(Messages.EDUCATION_END_DATE_IS_EARLIER_THAN_START_DATE);
        }

        // education Term already exists
        if (educationTermRepository.existsByTermAndYear(educationTermRequest.getTerm(), educationTermRequest.getStartDate().getYear())){
            throw new ResourceNotFoundException(Messages.EDUCATION_TERM_IS_ALREADY_EXISTS_BY_TERM_AND_YEAR);
        }
    }

    public List<EducationTermResponse> getAllEducationTerms() {
        return educationTermRepository.findAll()
                .stream()
                .map(educationTermDto::mapEducationTermToEducationTermResponse)
                .collect(Collectors.toList());
    }
    public EducationTermResponse getAllEducationTermsById(Long id){
        EducationTerm term = checkEducationTermExists(id);
        return educationTermDto.mapEducationTermToEducationTermResponse(term);

    }
}
