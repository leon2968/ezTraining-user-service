package com.eztraining.userservice.dao;

import com.eztraining.userservice.bean.StudentCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseInfoDao extends JpaRepository<StudentCourseInfo,Integer> {
    List<StudentCourseInfo> findStudentCourseInfosByStudentId(int sid);
    List<StudentCourseInfo> findStudentCourseInfosByCourseId(int cid);
    Optional<StudentCourseInfo> findStudentCourseInfoByStudentIdAndCourseId(int sid, int cid);

    //@Query("select sci from StudentCourseInfo sci where sci.approved = false and sci.courseId=:cid")
    List<StudentCourseInfo> findStudentCourseInfosByApprovedAndAndCourseId(boolean approved, int cid);

    //@Query("select sci from StudentCourseInfo sci where sci.approved = false")
}
