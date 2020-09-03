package com.luv2code.demo.Repository;

import com.luv2code.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public List<Customer> getAllByOrderByFirstName();

}
