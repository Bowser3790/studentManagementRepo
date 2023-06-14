package com.project.studentmgtsystemproject.payload.request;


import com.project.studentmgtsystemproject.payload.request.abstracts.BaseUserRequests;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AdminRequest extends BaseUserRequests {

    private boolean builtIn;



}
