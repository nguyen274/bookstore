package com.project.library.service;



import com.project.library.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAllStaff();

    void addNew(Staff staff);

    Staff saveStaff(Staff staff);

    void deleteStaff(Long id);

    Optional<Staff> findStaffById(Long id);

    List<Staff> getAllBySort();
}
