package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.Course;
import com.project.library.repository.CourseRepository;
import com.project.library.service.CourseService;


@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAllCourse() {
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public void addNew(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public Course saveCourse(Course course) {
		courseRepository.save(course);
        return course;
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
		
	}

	@Override
	public Optional<Course> findCourseById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public List<Course> getAllBySort() {
		return courseRepository.findAllByOrderByCourseNameAsc();
	}

}
