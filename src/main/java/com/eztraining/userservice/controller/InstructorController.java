package com.eztraining.userservice.controller;

import com.eztraining.userservice.bean.Instructor;
import com.eztraining.userservice.bean.Student;
import com.eztraining.userservice.http.Response;
import com.eztraining.userservice.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @PostMapping("/register")
    public Response registerInstructor(@RequestBody Instructor instructor){

        return instructorService.register(instructor);
    }

    @PutMapping("/update")
    public Response updateInstructor(@RequestBody Instructor instructor){

        return instructorService.update(instructor);
    }

    @GetMapping("/{id}")
    public Mono<Instructor> getInstructor(@PathVariable int id){

        return instructorService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @GetMapping("/uid/{uid}")
    public Mono<Instructor> getInstructorByUid(@PathVariable int uid){
        System.out.println("controller is hit");
        return instructorService.findByUserId(uid);
    }

    @GetMapping("/")
    public Flux<Instructor> getAllInstructors(){

        return instructorService.findAllInstructors();
    }

    @GetMapping("/search")
    public Flux<Instructor> getInstructorsByName(@RequestParam String name){

        return instructorService.findInstructorsByName(name);
    }
}
