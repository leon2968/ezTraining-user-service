package com.eztraining.userservice.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DEPARTMENT")
public class Department {
    @Id
    @SequenceGenerator(name = "department_seq_gen", sequenceName = "DEPARTMENT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator="department_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
