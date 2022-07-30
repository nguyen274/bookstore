package com.project.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.library.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
	public List<Producer> findAllByOrderByProducerNameAsc();
}
