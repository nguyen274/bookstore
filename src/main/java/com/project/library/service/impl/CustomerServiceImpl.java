package com.project.library.service.impl;

import com.project.library.model.Customer;
import com.project.library.repository.CustomerRepository;
import com.project.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void addNew(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllBySort() {
        return customerRepository.findAllByOrderByCustomerNameAsc();
    }


}
