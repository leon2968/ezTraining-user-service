package com.eztraining.userservice.service;

import com.eztraining.userservice.bean.Feedback;
import com.eztraining.userservice.dao.FeedbackDao;
import com.eztraining.userservice.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    FeedbackDao feedbackDao;
    public Mono<Feedback> findById(int id) {
        Optional<Feedback> op = feedbackDao.findById(id);
        return Mono.justOrEmpty(op);
    }

    public Flux<Feedback> findByInstructorId(int cid) {
        List<Feedback> feedbacks = feedbackDao.findAllByInstructorId(cid);
        return Flux.fromIterable(feedbacks);
    }

    public Flux<Feedback> findByStudentId(int sid) {
        List<Feedback> feedbacks = feedbackDao.findAllByStudentId(sid);
        return Flux.fromIterable(feedbacks);
    }

    public Response deleteById(int id) {
        Optional<Feedback> op = feedbackDao.findById(id);
        Response response = new Response(false);
        if(op.isPresent()){
            feedbackDao.deleteById(id);
            response.setSuccess(true);
            response.setMessage("id: " + id + " feedback is removed!");
        }
        return response;
    }

    public Response create(Feedback feedback) {
        if(isFeedbackExists(feedback)==null) {
            feedbackDao.save(feedback);
            return new Response(true);
        }
        return new Response(false);
    }

    public Response update(Feedback feedback) {
        if(isFeedbackExists(feedback)!=null){
            feedbackDao.save(feedback);
            return new Response(true);
        }
        return new Response(false);
    }

    public Feedback isFeedbackExists(Feedback feedback){
        int sid = feedback.getStudentId();
        int iid = feedback.getInstructorId();
        int cid = feedback.getCourseId();
        Optional<Feedback> op = feedbackDao.findByStudentIdAndAndInstructorIdAndAndCourseId(sid, iid, cid);
        return op.orElse(null);
    }
}
