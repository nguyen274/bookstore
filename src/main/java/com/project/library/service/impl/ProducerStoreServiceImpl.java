package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.library.model.Producer;
import com.project.library.repository.ProducerRepository;
import com.project.library.service.ProducerService;

@Service
public class ProducerStoreServiceImpl implements ProducerService{
	
	@Autowired
	private ProducerRepository producerRepository;

	@Override
	public List<Producer> getAllProducer() {
		return (List<Producer>) producerRepository.findAll();
	}

	@Override
	public void addNew(Producer producer) {
		producerRepository.save(producer);
		
	}

	@Override
	public Producer saveProducer(Producer producer) {
		producerRepository.save(producer);
		return producer;
	}

	@Override
	public void deleteProducer(Long id) {
		producerRepository.deleteById(id);
		
	}

	@Override
	public Optional<Producer> findProducerById(Long id) {
		return producerRepository.findById(id);
	}

	@Override
	public List<Producer> getAllBySort() {
		return producerRepository.findAllByOrderByProducerNameAsc();
	}

}
