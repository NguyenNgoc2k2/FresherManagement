package com.example.freshermanagement.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
@Data
public class Admin extends User{
}
