package com.example.freshermanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Task")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @PrePersist
    protected void onCreate(){
        this.startDate = new Date();
    }
}
