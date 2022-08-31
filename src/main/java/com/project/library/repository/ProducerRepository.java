package com.project.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.project.library.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
	public List<Producer> findAllByOrderByProducerNameAsc();

	@Query("Select count(s.producerCode) from Producer s where s.producerCode = ?1")
	public Integer findCode(String code);

	@Query("Select count(s.producerName) from Producer s where s.producerName = ?1")
	public Integer findName(String name);
}
