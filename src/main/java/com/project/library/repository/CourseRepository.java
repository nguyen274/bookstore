package com.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.library.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    public List<Course> findAllByOrderByCourseNameAsc();
}
