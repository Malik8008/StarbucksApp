package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.productDtos.getProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDiscountService {
    ResponseEntity<List<getProduct>> addDiscountForCategory(Long categoryId,int discount);
}
