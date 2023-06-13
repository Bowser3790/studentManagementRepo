package com.project.studentmgtsystemproject.entity.concretes;

import com.project.studentmgtsystemproject.entity.abstracts.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ViceDean extends User {

}
