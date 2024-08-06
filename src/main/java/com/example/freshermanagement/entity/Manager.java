package com.example.freshermanagement.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Manager")
@Data
public class Manager extends User{

    @OneToMany(mappedBy = "manager")
    private List<Management> managements;
}
