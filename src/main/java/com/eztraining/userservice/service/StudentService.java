package com.eztraining.userservice.service;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.bean.Instructor;
import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.dao.StudentDao;
import com.eztraining.userservice.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;
    @Autowired
    DepartmentService departmentService;

    public Response register(@RequestBody Student student){
        studentDao.save(student);
        return new Response(true);
    }

    public Response update(@RequestBody Student student){
        studentDao.save(student);
        return new Response(true);
    }

    public Flux<Student> getStudentsByDepartment(@RequestParam int deptId){
        Department dept = departmentService.findById(deptId).block();
        List<Student> students= studentDao.findByDepartment(dept);
        return Flux.fromIterable(students);
    }

    public Mono<Student> findById(int id) {
        Optional<Student> op = studentDao.findById(id);
        return Mono.justOrEmpty(op);
    }

    public Flux<Student> findAllStudents() {
        List<Student> students = studentDao.findAll();
        return Flux.fromIterable(students);
    }

    public Flux<Student> findStudentsByName(String name) {
        List<Student> students = studentDao.findStudentsByNameContains(name);
        return Flux.fromIterable(students);
    }
}
