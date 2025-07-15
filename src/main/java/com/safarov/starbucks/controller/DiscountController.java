package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.productDtos.getProduct;
import com.safarov.starbucks.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final ProductService productService;

    @PutMapping("/{categoryId}")
    public ResponseEntity<List<getProduct>> applyDiscountToCategory(@PathVariable Long categoryId, @RequestParam int discount) {
        return productService.addDiscountForCategory(categoryId,discount);
    }
}
