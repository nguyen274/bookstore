package com.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.library.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    public List<Student> findAllByOrderByStudentNameAsc();
}
