package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.Student;
import com.project.library.repository.StudentRepository;
import com.project.library.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public void addNew(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public Student saveStudent(Student student) {
		studentRepository.save(student);
        return student;
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
		
	}

	@Override
	public Optional<Student> findStudentById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> getAllBySort() {
		return studentRepository.findAllByOrderByStudentNameAsc();
	}

}
