package com.project.studentmgtsystemproject.utils;

public class Messages {
    private Messages(){

    }
    public static String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";
    public static String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation";


    public static String ADMIN_DELETED_SUCCESSFULLY = "Admin deleted successfully";



    public static final String  ALREADY_REGISTER_MESSAGE_USERNAME = "Error: USER with username %s already register";
    public static final String ALREADY_REGISTER_MESSAGE_SSN = "Error: User with ssn %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_PHONE_NUMBER = "Error: User with phone number %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: User with email %s is already registered";

    // highlight words + command + shift + u => will take this code below as all lowercase and make it all uppercase

    public static final String ROLE_NOT_FOUND = "There is no role available that you are searching for.";
    public static final String ROLE_ALREADY_EXISTS_IN_DATABASE = "The database already has these roles available";


    public static final String ALREADY_SEND_A_MESSAGE_TODAY = "Error: You have already sent an message with this e-mail";

    // education term related messages

    public static final String EDUCATION_START_DATE_IS_EARLIER_THAN_LAST_REGISTRATION_DATE = "Error: The start date  cannot be earlier than the last registration date";
    public static final String EDUCATION_END_DATE_IS_EARLIER_THAN_START_DATE = "Error: The end date  cannot be earlier than the start date";
    public static final String EDUCATION_TERM_IS_ALREADY_EXISTS_BY_TERM_AND_YEAR = "Error: Education term already exists";
    public static final String EDUCATION_TERM_NOT_FOUND = "Error: Education term not found";

    public static final String NOT_FOUND_EDUCATION_TERM_BY_ID = "Error: Education term not found by id";

    public static  final String ALREADY_REGISTERED_LESSON_MESSAGE = "Error: Lesson name %s is already registered";

    public static  final String LESSON_MESSAGE_NOT_FOUND = "Error: Lesson name %s is already registered";


}
