package com.project.studentmgtsystemproject.controller;


import com.project.studentmgtsystemproject.entity.concretes.Admin;
import com.project.studentmgtsystemproject.payload.request.AdminRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.service.AdminService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid AdminRequest adminRequest) {

        return ResponseEntity.ok(adminService.save(adminRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<Admin>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "date", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {


        //TODO move this calculation to service layer --> homework. this is not good placement
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());

        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        // TODO Return type should be a DTO -> you never want to an Entity because this could result in an SQL injection.
        // in contact message service we mapped this to a DTO please use this code to make this change.
       // The DTO(AdminResponse) this should be done in SERVICE
        Page<Admin> admins = adminService.getAllAdmins((java.awt.print.Pageable) pageable);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    public ResponseEntity<String> delete(@PathVariable Long id){

    }
}

