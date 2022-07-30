package com.project.library.service;

import java.util.List;
import java.util.Optional;


import com.project.library.model.Producer;

public interface ProducerService {
	
    List<Producer> getAllProducer();
    
    void addNew(Producer producer);

    Producer saveProducer(Producer producer);

    void deleteProducer(Long id);

    Optional<Producer> findProducerById(Long id);
    
    List<Producer> getAllBySort();
}
