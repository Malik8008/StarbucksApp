package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.productDtos.getProduct;
import com.safarov.starbucks.dto.productDtos.postProduct;
import com.safarov.starbucks.dto.productDtos.putProduct;
import com.safarov.starbucks.payload.ApiResponse;
import com.safarov.starbucks.service.impl.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final Product productService;

    @GetMapping("/{id}")
    public ResponseEntity<getProduct> get(@PathVariable Long id) {
        return productService.get(id);
    }

    @GetMapping
    public ResponseEntity<List<getProduct>> getAll(){
        return productService.getAll();
    }

    @GetMapping("/categoriesID/{id}")
    public ResponseEntity<List<getProduct>> getCategoriesById(@PathVariable Long id) {
        return productService.getProductsByCategoryId(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<getProduct>> post(@RequestBody postProduct postProduct) {
        return productService.post(postProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<getProduct> put(@PathVariable Long id,@RequestBody putProduct putProduct) {
        return productService.put(id,putProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
