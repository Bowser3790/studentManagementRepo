package com.project.studentmgtsystemproject.controller;


import com.project.studentmgtsystemproject.payload.request.EducationTermRequest;
import com.project.studentmgtsystemproject.payload.response.EducationTermResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.EducationTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/educationTerms")
@RequiredArgsConstructor
public class EducationTermController {

    EducationTermService educationTermService;

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/save")
    public ResponseMessage<EducationTermResponse> saveEducationTerm(@RequestBody @Valid EducationTermRequest educationTermRequest){

        return educationTermService.saveEducationTerm(educationTermRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','TEACHER')")
    @GetMapping("/{id}")
    public EducationTermResponse getEducationTermById(@PathVariable Long id){
        return educationTermService.getEducationTermResponseById(id);


    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANTMANAGER','TEACHER')")
    @GetMapping("/getAll")
    public List<EducationTermResponse> getAllEducationTerms(){
        return educationTermService.getAllEducationTerms();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','TEACHER')")
    @GetMapping("/search")
    public Page<EducationTermResponse>getAllEducationTermsByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "startDate") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type){

        return educationTermService.getAllEducationTermsByPage(page,size,sort,type);

    }
    // TODO homework please please write down a request that gets all education term start dates later then  entered date
    // hint -> should be post request
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseMessage<EducationTermResponse>updateEducationTerm(@PathVariable Long id,
                                                                     @RequestBody @Valid EducationTermRequest educationTermRequest){
        return educationTermService.updateEducationTerm(id,educationTermRequest);
    }


}
