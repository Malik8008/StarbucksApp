package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.customerDtos.getCustomer;
import com.safarov.starbucks.dto.customerDtos.postCustomer;
import com.safarov.starbucks.dto.customerDtos.putCustomer;

import java.util.List;

public interface ICustomerService {
    public getCustomer getCustomer(Long customerId);
    public List<getCustomer> getAllCustomers();
    public getCustomer createCustomer(postCustomer customer);
    public getCustomer updateCustomer(Long customerId, putCustomer customer);
    public void deleteCustomer(Long customerId);
}
