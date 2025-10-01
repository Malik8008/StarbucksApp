package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;
import com.safarov.starbucks.service.impl.Category;
import com.safarov.starbucks.service.impl.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final Category categoryService;
    private final Product productService;

    @PostMapping
    public ResponseEntity<getCategory> save(@RequestBody postCategory category) {
        return new ResponseEntity<>(categoryService.post(category), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<getCategory> get(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<getCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
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
