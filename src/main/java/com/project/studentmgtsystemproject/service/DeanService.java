package com.project.studentmgtsystemproject.service;


import com.project.studentmgtsystemproject.payload.request.DeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeanService {
    AdminService adminService;

    public ResponseMessage<DeanResponse> save(DeanRequest deanRequest){
        adminService.checkDuplicate();
    }

}
