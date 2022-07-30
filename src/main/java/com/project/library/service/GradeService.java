package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.Grade;


public interface GradeService {
	    List<Grade> getAllGrade();

    void  addNew(Grade grade);

    Grade saveGrade(Grade grade);

    void deleteGrade(Long id);

    Optional<Grade> findGradeById(Long id);

	List<Grade> getAllBySort();

}
