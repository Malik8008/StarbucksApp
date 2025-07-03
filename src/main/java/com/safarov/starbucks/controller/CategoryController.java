package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;
import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.model.Product;
import com.safarov.starbucks.service.impl.CategoryService;
import com.safarov.starbucks.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<getCategory> save(@RequestBody postCategory category) {
        return new ResponseEntity<>(categoryService.post(category), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<getCategory> get(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<getCategory> update(@PathVariable Long id, @RequestBody putCategory category) {
        return new ResponseEntity<>(categoryService.put(id,category),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<getCategory> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
