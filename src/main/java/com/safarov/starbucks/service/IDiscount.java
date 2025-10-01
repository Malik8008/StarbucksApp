package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.productDtos.getProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDiscount {
    ResponseEntity<List<getProduct>> addDiscountForCategory(Long categoryId,int discount);
}
