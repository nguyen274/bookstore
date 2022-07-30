package com.project.library.service;



import com.project.library.model.Major;

import java.util.List;
import java.util.Optional;

public interface MajorService {
    List<Major> getAllMajor();

    void addNew(Major major);

    Major saveMajor(Major major);

    void deleteMajor(Long id);

    Optional<Major> findMajorById(Long id);

    List<Major> getAllBySort();
}
