package com.project.library.repository;

import com.project.library.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public List<Staff> findAllByOrderByStaffNameAsc();

    @Query("Select count(s.staffCode) from Staff s where s.staffCode = ?1")
    public Integer findCode(String code);

    @Query("Select count(s.phoneNumber) from Staff s where s.phoneNumber = ?1")
    public Integer findPhone(String phone);
}
