package com.eztraining.userservice.service;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.bean.Instructor;
import com.eztraining.userservice.dao.InstructorDao;
import com.eztraining.userservice.http.Response;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    @Autowired
    InstructorDao instructorDao;
    @Autowired
    DepartmentService departmentService;

    public Response register(@RequestBody Instructor instructor){
        instructorDao.save(instructor);
        return new Response(true);
    }

    public Response update(@RequestBody Instructor instructor){
        instructorDao.save(instructor);
        return new Response(true);
    }

    public Flux<Instructor> getInstructorsByDepartment(@RequestParam int deptId){
        Department dept = departmentService.findById(deptId).block();
        List<Instructor> instructors= instructorDao.findAllByDepartment(dept);
        return Flux.fromIterable(instructors);
    }

    public Mono<Instructor> findById(int id) {
        Optional<Instructor> op = instructorDao.findById(id);
        return Mono.justOrEmpty(op);
    }

    public Flux<Instructor> findAllInstructors() {
        List<Instructor> instructors= instructorDao.findAll();
        return Flux.fromIterable(instructors);
    }

    public Flux<Instructor> findInstructorsByName(String name) {
        List<Instructor> instructors = instructorDao.findInstructorsByNameContains(name);
        return Flux.fromIterable(instructors);
    }

    public Mono<Instructor> findByUserId(int uid) {
        Optional<Instructor> op = instructorDao.findByUserId(uid);
        return Mono.justOrEmpty(op);
    }
}
