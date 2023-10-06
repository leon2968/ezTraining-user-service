package com.eztraining.userservice.controller;

import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.bean.StudentCourseInfo;
import com.eztraining.userservice.dto.Course;
import com.eztraining.userservice.http.Response;
import com.eztraining.userservice.service.StudentCourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/studentCourseInfo")
public class StudentCourseInfoController {

    @Autowired
    StudentCourseInfoService studentCourseInfoService;

    //for student
    @PostMapping("/enrollment")
    public Response enrollCourse(@RequestBody StudentCourseInfo sci){
        return studentCourseInfoService.enrollCourse(sci);
    }


    //for student
    @PostMapping("/withdraw")
    public Response withdrawCourse(@RequestBody StudentCourseInfo sci){
        return studentCourseInfoService.withdrawCourse(sci);
    }

    //for admin only?
    @GetMapping("/viewDetail")
    public Mono<StudentCourseInfo> getStudentCourseInfo(@RequestBody StudentCourseInfo sci){
        return studentCourseInfoService.getStudentCourseInfo(sci);
    }

    //for student
    @GetMapping("/student/{sid}")
    public Flux<StudentCourseInfo> getAllCoursesEnrolledByStudentId(@PathVariable int sid){
        return studentCourseInfoService.getAllCoursesEnrolledByStudentId(sid);
    }

    //for instructors
    @GetMapping("/course/{cid}")
    public Flux<StudentCourseInfo> getAllStudentsEnrolledByCourseId(@PathVariable int cid){
        return studentCourseInfoService.getAllStudentsEnrolledByCourseId(cid);
    }
}
