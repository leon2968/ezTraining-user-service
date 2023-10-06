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
@Table(name="STUDENT_COURSE_INFO")
public class StudentCourseInfo {
    @Id
    @SequenceGenerator(name = "sci_seq_gen", sequenceName = "SCI_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator="sci_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    private boolean approved;
    private boolean passed;
    private Date enrollmentDate;
    private int finalScore;

}
