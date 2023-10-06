package com.eztraining.userservice.service;

import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.bean.StudentCourseInfo;
import com.eztraining.userservice.dao.StudentCourseInfoDao;
import com.eztraining.userservice.dto.Course;
import com.eztraining.userservice.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseInfoService {
    @Autowired
    StudentCourseInfoDao studentCourseInfoDao;

    public Response enrollCourse(StudentCourseInfo sci){
        int courseId = sci.getCourseId();
        int studentId = sci.getStudentId();
        Optional<StudentCourseInfo> op = studentCourseInfoDao.findStudentCourseInfoByStudentIdAndCourseId(studentId, courseId);
        if(op.isPresent()){
            return new Response(false, "Enrolled already");
        }

//        StudentCourseInfo sci = new StudentCourseInfo();
//        sci.setCourseId(courseId);
//        sci.setStudentId(studentId;
        studentCourseInfoDao.save(sci);
        return new Response(true);
    }

    public Response withdrawCourse(StudentCourseInfo sci){
        Response response = new Response(false, "Course can not be withdrawn");

        int courseId = sci.getCourseId();
        int studentId = sci.getStudentId();
        Optional<StudentCourseInfo> op = studentCourseInfoDao.findStudentCourseInfoByStudentIdAndCourseId(studentId, courseId);
        if(op.isPresent()){
            studentCourseInfoDao.delete(op.get());
            response.setSuccess(true);
            response.setMessage("The course has been withdrawn successfully");
        }
        return response;
    }

    public Mono<StudentCourseInfo> getStudentCourseInfo(StudentCourseInfo sci) {
        int courseId = sci.getCourseId();
        int studentId = sci.getStudentId();
        Optional<StudentCourseInfo> op = studentCourseInfoDao.findStudentCourseInfoByStudentIdAndCourseId(studentId, courseId);
        return Mono.justOrEmpty(op);
    }

    public Flux<StudentCourseInfo> getAllCoursesEnrolledByStudentId(int sid) {
        List<StudentCourseInfo> sciList = studentCourseInfoDao.findStudentCourseInfosByStudentId(sid);
        return Flux.fromIterable(sciList);
    }

    public Flux<StudentCourseInfo> getAllStudentsEnrolledByCourseId(int cid) {
        List<StudentCourseInfo> sciList = studentCourseInfoDao.findStudentCourseInfosByCourseId(cid);
        return Flux.fromIterable(sciList);
    }
}
