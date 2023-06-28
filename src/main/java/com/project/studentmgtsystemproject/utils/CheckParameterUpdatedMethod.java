package com.project.studentmgtsystemproject.utils;

import com.project.studentmgtsystemproject.entity.abstracts.User;
import com.project.studentmgtsystemproject.payload.request.abstracts.BaseUserRequests;

public class CheckParameterUpdatedMethod {

    /**
     *
     * @param user a kind of entity that will be validated
     * @param baseUserRequests DTO from UI to be changed
     * @return true if they are the same
     *
     * why are we using equalsIgnoreCase for SSN and PhoneNumber?
     */

    public static boolean checkUniqueProperties(User user, BaseUserRequests baseUserRequests){
        return user.getSsn().equalsIgnoreCase(baseUserRequests.getSsn())
                || user.getPhoneNumber().equalsIgnoreCase(baseUserRequests.getPhoneNumber())
                || user.getUsername().equalsIgnoreCase(baseUserRequests.getUsername());
    }
}
