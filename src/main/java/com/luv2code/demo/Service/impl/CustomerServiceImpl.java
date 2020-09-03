package com.luv2code.demo.Service.impl;

import com.luv2code.demo.Entity.Customer;
import com.luv2code.demo.Repository.CustomerRepository;
import com.luv2code.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("customerService")
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllSortedByFirstName() {
        return customerRepository.getAllByOrderByFirstName();
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
//        return customerList.stream()
//                .sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()))
//                .collect(Collectors.toList());

    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id) ;
    }


}
