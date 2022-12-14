package com.project.library.service;

import com.project.library.model.Customer;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomer();

    void addNew(Customer customer);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    Optional<Customer> findCustomerById(Long id);

    List<Customer> getAllBySort();

    Integer checkUniquePhone(@Param("customerCode")String phoneInput);
    Integer checkUniqueEmail(@Param("email")String emailInput);

}
