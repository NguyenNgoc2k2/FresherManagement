package com.example.freshermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(RoleName roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName.toString();
    }
}
