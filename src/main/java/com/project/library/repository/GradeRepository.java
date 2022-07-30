package com.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.project.library.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	public List<Grade> findAllByOrderByGradeNameAsc();
}
