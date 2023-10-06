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
@Table(name="INSTRUCTOR")
public class Instructor {
    @Id
    @SequenceGenerator(name = "instructor_seq_gen", sequenceName = "INSTRUCTOR_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator="instructor_seq_gen", strategy = GenerationType.AUTO)
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