package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.Course;

public interface CourseService {
	List<Course> getAllCourse();

	void addNew(Course course);

	Course saveCourse(Course course);

	void deleteCourse(Long id);

	Optional<Course> findCourseById(Long id);

    List<Course> getAllBySort();
}
