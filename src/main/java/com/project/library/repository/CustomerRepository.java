package com.project.library.repository;

import com.project.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findAllByOrderByCustomerNameAsc();

}
