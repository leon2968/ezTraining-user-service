package com.eztraining.userservice.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="FEEDBACK")
public class Feedback {
    @Id
    @SequenceGenerator(name = "feedback_seq_gen", sequenceName = "FEEDBACK_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator="feedback_seq_gen", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, insertable = false, updatable = false)
    private Student student;

    @Column(name = "student_id")
    private int studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false, insertable = false, updatable = false)
    private Instructor instructor;

    @Column(name = "instructor_id")
    private int instructorId;

    @Column(name ="course_id")
    private int courseId;

    private String content;

    public int getStudentId() {
        return student.getId();
    }
}