package com.project.library.repository;

import com.project.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findAllByOrderByCustomerNameAsc();

    @Query("Select count(s.customerCode) from Customer s where s.customerCode = ?1")
    public Integer findPhone(String phone);

    @Query("Select count(s.email) from Customer s where s.email = ?1")
    public Integer findEmail(String email);

}
