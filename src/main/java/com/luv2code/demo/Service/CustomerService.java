package com.luv2code.demo.Service;

import com.luv2code.demo.Entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public List<Customer> getAllSortedByFirstName();
    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
    public Customer findById(Integer id) ;
    public void deleteById(Integer id);
}
