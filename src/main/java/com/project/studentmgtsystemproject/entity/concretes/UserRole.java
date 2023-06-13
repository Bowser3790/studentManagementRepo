package com.project.studentmgtsystemproject.entity.concretes;


import com.project.studentmgtsystemproject.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING) // this means that the role type will be stored in the DB as their string names vs -0,1,2,3 etc.
    @Column(length = 20)
    private RoleType roleType;

}
