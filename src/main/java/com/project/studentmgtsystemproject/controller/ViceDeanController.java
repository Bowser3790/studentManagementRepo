package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.payload.request.ViceDeanRequest;
import com.project.studentmgtsystemproject.payload.response.DeanResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.payload.response.ViceDeanResponse;
import com.project.studentmgtsystemproject.service.ViceDeanService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vicedean")
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class ViceDeanController {

    private final ViceDeanService viceDeanService;


    @PostMapping("/save")
    public ResponseMessage<ViceDeanResponse> saveViceDean(@RequestBody @Valid ViceDeanRequest viceDeanRequest) {
        return viceDeanService.saveViceDean(viceDeanRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PutMapping("/update{userId}")  // just not that {userId} and userId parameters below should be the same.  Or you will not see this accepted on POSTMAN.
    public ResponseMessage<ViceDeanResponse> updateViceDean(@RequestBody @Valid ViceDeanRequest viceDeanRequest
                                                            ,@PathVariable Long userId){
        return viceDeanService.updateViceDean(viceDeanRequest,userId);

    }

    // implement 2 methods Delete By Id, and findById
    @DeleteMapping("/delete/{userId}")
    public ResponseMessage<?> deleteViceDeanById(@PathVariable Long userId) {

        return viceDeanService.deleteViceDeanById(userId);
    }

    // TODO Homework (write this delete message again with requests param


    @GetMapping("/getViceDeanById/{userId}")
    public ResponseMessage<ViceDeanResponse> getViceDeanById(@PathVariable Long userId) {
        return viceDeanService.getViceDeanById(userId);

    }
}