package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.Grade;
import com.project.library.repository.GradeRepository;
import com.project.library.service.GradeService;


@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public List<Grade> getAllGrade() {
		return (List<Grade>) gradeRepository.findAll();
	}

	@Override
	public void addNew(Grade grade) {
		gradeRepository.save(grade);
		
	}

	@Override
	public Grade saveGrade(Grade grade) {
		gradeRepository.save(grade);
        return grade;
	}

	@Override
	public void deleteGrade(Long id) {
		gradeRepository.deleteById(id);
		
	}

	@Override
	public Optional<Grade> findGradeById(Long id) {
		return gradeRepository.findById(id);
	}

	@Override
	public List<Grade> getAllBySort() {
		// TODO Auto-generated method stub
		return gradeRepository.findAllByOrderByGradeNameAsc();
	}

}
