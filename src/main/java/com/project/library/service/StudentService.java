package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.Student;

public interface StudentService {

	List<Student> getAllStudent();

	void addNew(Student student);

	Student saveStudent(Student student);

	void deleteStudent(Long id);

	Optional<Student> findStudentById(Long id);

	List<Student> getAllBySort();
}
