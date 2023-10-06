package com.eztraining.userservice.service;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.dao.DepartmentDao;
import com.eztraining.userservice.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    public Mono<Department> findById(int id){
        Optional<Department> op = departmentDao.findById(id);
        return Mono.just(op.get());
    }

    public Response create(Department department) {
        System.out.println("creating department...");
        departmentDao.save(department);
        return new Response(true, department.toString());
    }

    public Response updateById(int id, Department department) {
        Response response = new Response(false);
        Optional<Department> op = departmentDao.findById(id);
        if(op.isPresent()){
            Department deptObj = op.get();
            deptObj.setName(department.getName());
            departmentDao.save(deptObj);
            response.setSuccess(true);
        }
        return response;
    }
}
