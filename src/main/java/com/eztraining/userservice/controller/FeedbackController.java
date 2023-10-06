package com.eztraining.userservice.controller;

import com.eztraining.userservice.bean.Feedback;
import com.eztraining.userservice.http.Response;
import com.eztraining.userservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/new")
    public Response create(@RequestBody Feedback feedback){
        return feedbackService.create(feedback);
    }

    @GetMapping("/{id}")
    public Mono<Feedback> getById(@PathVariable  int id){
        return feedbackService.findById(id);
    }

    @PutMapping("/{id}")
    public Response updateById(@PathVariable  int id, @RequestBody Feedback feedback){
        return feedbackService.update(feedback);
    }

    @DeleteMapping("/{id}")
    public Response deleteById(@PathVariable  int id){
        return feedbackService.deleteById(id);
    }

    @GetMapping("/instructor/{cid}")
    public Flux<Feedback> getByInstructorId(@PathVariable int cid){
        return feedbackService.findByInstructorId(cid);
    }

    @GetMapping("/student/{sid}")
    public Flux<Feedback> getByStudentId(@PathVariable int sid){
        return feedbackService.findByStudentId(sid);
    }


}
