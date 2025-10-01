package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.customerDtos.getCustomer;
import com.safarov.starbucks.dto.customerDtos.postCustomer;
import com.safarov.starbucks.dto.customerDtos.putCustomer;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.repository.CustomerRepository;
import com.safarov.starbucks.service.ICustomer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Customer implements ICustomer {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public getCustomer getCustomer(Long customerId) {
        com.safarov.starbucks.model.Customer customer = customerRepository.findByDeletedFalseAndId(customerId);
        if (customer == null) {
            throw new IdNotFoundException("Customer id is empty");
        }
        return modelMapper.map(customer,getCustomer.class);
    }

    @Override
    public List<getCustomer> getAllCustomers() {
        List<com.safarov.starbucks.model.Customer> customers = customerRepository.findAllByDeletedFalse();
        return customers.stream().map(customer -> modelMapper.map(customer,getCustomer.class)).collect(Collectors.toList());
    }

    @Override
    public getCustomer createCustomer(postCustomer customer) {
        com.safarov.starbucks.model.Customer newCustomer = modelMapper.map(customer, com.safarov.starbucks.model.Customer.class);
        return modelMapper.map(customerRepository.save(newCustomer),getCustomer.class);
    }

    @Override
    public getCustomer updateCustomer(Long customerId, putCustomer customer) {
        com.safarov.starbucks.model.Customer oldCustomer = customerRepository.findByDeletedFalseAndId(customerId);
        if (oldCustomer == null) {
            throw new IdNotFoundException("Customer id is empty");
        }
        oldCustomer.setId(customerId);
        oldCustomer.setName(customer.getName());
        customerRepository.save(oldCustomer);
        return modelMapper.map(oldCustomer,getCustomer.class);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        com.safarov.starbucks.model.Customer oldCustomer = customerRepository.findById(customerId).orElseThrow(() -> new IdNotFoundException("Customer id not found"));
        oldCustomer.setDeleted(true);
        customerRepository.save(oldCustomer);
    }
}
