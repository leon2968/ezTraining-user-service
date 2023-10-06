package com.eztraining.userservice.dao;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.bean.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorDao extends JpaRepository<Instructor,Integer> {

    List<Instructor> findAllByDepartment(Department dept);

    List<Instructor> findAll();

    List<Instructor> findInstructorsByNameContains(String name);

    Optional<Instructor> findByUserId(int uid);
}
