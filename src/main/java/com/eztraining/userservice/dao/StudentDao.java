package com.eztraining.userservice.dao;

import com.eztraining.userservice.bean.Department;
import com.eztraining.userservice.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    Student findByName(String name);
    List<Student> findByDepartment(Department department);


    List<Student> findStudentsByNameContains(String name);
}
