package com.project.library.service.impl;

import com.project.library.model.Major;
import com.project.library.repository.MajorRepository;
import com.project.library.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getAllMajor() {
        return (List<Major>) majorRepository.findAll();
    }

    @Override
    public void addNew(Major major) {
        majorRepository.save(major);
    }

    @Override
    public Major saveMajor(Major major) {
        majorRepository.save(major);
        return major;
    }

    @Override
    public void deleteMajor(Long id) {
        majorRepository.deleteById(id);
    }

    @Override
    public Optional<Major> findMajorById(Long id) {
        return majorRepository.findById(id);
    }

    @Override
    public List<Major> getAllBySort() {
        return majorRepository.findAllByOrderByMajorNameAsc();
    }
}
