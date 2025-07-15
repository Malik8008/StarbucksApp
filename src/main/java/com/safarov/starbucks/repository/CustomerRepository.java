package com.safarov.starbucks.repository;

import com.safarov.starbucks.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByDeletedFalseAndId(Long id);
    List<Customer> findAllByDeletedFalse();
}
