package com.project.library.repository;


import com.project.library.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    public List<Major> findAllByOrderByMajorNameAsc();
}
