package com.eztraining.userservice.dao;

import com.eztraining.userservice.bean.Feedback;
import com.eztraining.userservice.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByStudent(Student student);

    List<Feedback> findAllByStudentId(int id);

    List<Feedback> findAllByInstructorId(int iid);

    Optional<Feedback>  findByStudentIdAndAndInstructorIdAndAndCourseId(int sic, int iid, int cid);
}
