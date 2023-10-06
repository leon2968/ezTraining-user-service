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
@Table(name="STUDENT")
public class Student {
    @Id
    @SequenceGenerator(name = "student_seq_gen", sequenceName = "STUDENT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator="student_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String phone;
    private String email;
    private String address;
    private Date startingDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private int userId;



}
