package com.example.freshermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TransferLog")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "des")
    private String des;

    @JsonIgnoreProperties({"TransferLog", "courses"})
    @ManyToOne
    @JoinColumn(name = "center1_id", nullable = false)
    private Center oldCenter1;

    @JsonIgnoreProperties({"TransferLog", "courses"})
    @ManyToOne
    @JoinColumn(name = "center2_id", nullable = false)
    private Center oldCenter2;

    @JsonIgnoreProperties({"TransferLog", "courses"})
    @ManyToOne
    @JoinColumn(name = "new_center_id", nullable = false)
    private Center newCenter;
}
