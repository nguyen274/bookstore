package com.project.library.service;

import java.util.List;
import java.util.Optional;


import com.project.library.model.Producer;
import org.springframework.data.repository.query.Param;

public interface ProducerService {
	
    List<Producer> getAllProducer();
    
    void addNew(Producer producer);

    Producer saveProducer(Producer producer);

    void deleteProducer(Long id);

    Optional<Producer> findProducerById(Long id);
    
    List<Producer> getAllBySort();

    Integer checkUniqueCode(@Param("producerCode")String codeInput);

    Integer checkUniqueName(@Param("producerName")String nameInput);
}
