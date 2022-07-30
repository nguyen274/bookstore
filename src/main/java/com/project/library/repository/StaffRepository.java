package com.project.library.repository;

import com.project.library.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public List<Staff> findAllByOrderByStaffNameAsc();
}
