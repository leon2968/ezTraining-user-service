package com.eztraining.userservice.controller;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.bean.Instructor;
import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.http.Response;
import com.eztraining.userservice.service.DepartmentService;
import com.eztraining.userservice.service.InstructorService;
import com.eztraining.userservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final StudentService studentService;
    private DepartmentService departmentService;
    private InstructorService instructorService;

    @Autowired
    public DepartmentController(StudentService studentService, InstructorService instructorService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public Mono<Department> getById(@PathVariable int id) {
        return departmentService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public Response create(@RequestBody Department department) {
        System.out.println("0: creating department...");
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public Response updateById(@PathVariable int id, @RequestBody Department deparment) {
        return departmentService.updateById(id, deparment);
    }


    @GetMapping("/{id}/students")
    public Flux<Student> getStudentsByDepartment(@PathVariable int deptId) {

        return studentService.getStudentsByDepartment(deptId);
    }

    @GetMapping("/{id}/instructors")
    public Flux<Instructor> getInstructorsByDepartment(@PathVariable int deptId) {

        return instructorService.getInstructorsByDepartment(deptId);
    }
}