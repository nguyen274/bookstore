package com.project.library.service.impl;

import com.project.library.model.Staff;
import com.project.library.repository.StaffRepository;
import com.project.library.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return (List<Staff>) staffRepository.findAll();
    }

    @Override
    public void addNew(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        staffRepository.save(staff);
        return staff;
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Optional<Staff> findStaffById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public List<Staff> getAllBySort() {
        return (List<Staff>) staffRepository.findAllByOrderByStaffNameAsc();
    }
}
