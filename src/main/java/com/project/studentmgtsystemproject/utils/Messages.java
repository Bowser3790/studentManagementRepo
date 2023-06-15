package com.project.studentmgtsystemproject.utils;

public class Messages {
    private Messages(){

    }

    public static final String  ALREADY_REGISTER_MESSAGE_USERNAME = "Error: USER with username %s already register";

    public static final String ALREADY_REGISTER_MESSAGE_SSN = "Error: User with ssn %s is already registered";

    public static final String ALREADY_REGISTER_MESSAGE_PHONE_NUMBER = "Error: User with phone number %s is already registered";

    // highlight words + command + shift + u => will take this code below as all lowercase and make it all uppercase
    public static final String ALREADY_SEND_A_MESSAGE_TODAY = "Error: You have already sent an message with this e-mail";

    public static final String ROLE_NOT_FOUND = "There is no role available that you are searching for.";

    public static final String ROLE_ALREADY_EXISTS_IN_DATABASE = "The database already has these roles available";
}
