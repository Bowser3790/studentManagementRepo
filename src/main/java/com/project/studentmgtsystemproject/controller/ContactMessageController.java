package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.payload.request.ContactMessageRequest;
import com.project.studentmgtsystemproject.payload.response.ContactMessageResponse;
import com.project.studentmgtsystemproject.payload.response.ResponseMessage;
import com.project.studentmgtsystemproject.service.ContactMessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("contactMessages")
@RequiredArgsConstructor

public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    // add endpoint => check the database if there are any messages
    /*
    ********* DOCKER ****** Important ************
    The Database Stack on the right side of intellij you will see the Database Tab.
    Video Day 3 project  ** 1 hr 10 min **
    * you can add your database credentials click on the database tab => + (add)
    * Data Source => click PostgresSQL => the tabs will have all tables from your project
    * You can also write queries here.
    * if you open the tab with the wrench it will open project Data Sources => docker
    * you have to add these values: Host-> localhost, Port: 5432 (default)
    * Enter username and password -> this exists in application properties for postgresSQL (same user and pass)
    * (not necessary) same info from above) you can use a plugin on the Left SideBar need to find how to get the DB Browser Tab and this is a plugin for connections to docker..
    * the Box with SQL 2nd to last tab is a Query interface where you can write SQL and query your database.
    * Such as SELECT * FROM school_management_base.->> try to use this and solve yourself.

     */

    /*

    It's extremely important to document what everything does below:

    * @param page number of selected page
    * @param size size of the page
    * @param sort sort property
    * @param type DESC or ASC
    * @return ContactMessageResponse

     */


    @PostMapping("/save") // "save"
    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest) {
        return contactMessageService.save(contactMessageRequest);
    }

    @GetMapping("/getAll")
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "date", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.getAll(page, size, sort, type);
    }

    /**  NOTE how do you automatically populate this? -> /** and hit enter
     *
     * @param email
     * @param page
     * @param size
     * @param sort
     * @param type
     * @return
     */

    @GetMapping("/searchByEmail")
    public Page<ContactMessageResponse> searchByEmail(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "date", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.searchByEmail(email, page, size, sort, type);

    }

    @GetMapping("/searchBySubject")
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "date", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.searchBySubject(subject, page, size, sort, type);


    }
//    @DeleteMapping("/{id}")
//    public <ContactMessageResponse> ContactMessageController(
//            @PathVariable() Long id){
//        contactMessageService.deleteContactMessageById(id);
//        Map<String,String> map = new HashMap<>();
//        map.put("message", "Student deleted successfully");
//        map.put("status", "true");
//        return ContactMessageResponse(map, HttpStatus.CREATED);


    }

