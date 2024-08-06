package com.example.freshermanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Management")
@Data
public class Management extends Task {

    @JsonIgnoreProperties({"courses", "TransferLog"})
    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
