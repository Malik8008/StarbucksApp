package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.customerDtos.getCustomer;
import com.safarov.starbucks.dto.customerDtos.postCustomer;
import com.safarov.starbucks.dto.customerDtos.putCustomer;
import com.safarov.starbucks.service.impl.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Customer customerService;

    @GetMapping("{id}")
    public ResponseEntity<getCustomer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<List<getCustomer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping
    public ResponseEntity<getCustomer> createCustomer(@RequestBody postCustomer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("{id}")
    public ResponseEntity<getCustomer> updateCustomer(@PathVariable Long id, @RequestBody putCustomer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<getCustomer> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
