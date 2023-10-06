package com.eztraining.userservice.controller;

import com.eztraining.userservice.bean.Instructor;
import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.http.Response;
import com.eztraining.userservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public Response registerStudent(@RequestBody Student student){

        return studentService.register(student);
    }

    @PutMapping("/update")
    public Response updateStudent(@RequestBody Student student){

        return studentService.update(student);
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudent(@PathVariable int id){

        return studentService.findById(id);
    }

    @GetMapping("/")
    public Flux<Student> getAllStudents(){

        return studentService.findAllStudents();
    }

    @GetMapping("/search")
    public Flux<Student> getInstructorsByName(@RequestParam String name){
        return studentService.findStudentsByName(name);
    }

}
