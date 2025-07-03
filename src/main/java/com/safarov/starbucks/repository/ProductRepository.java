package com.safarov.starbucks.repository;

import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdAndDeleted(Long id,boolean deleted);
    List<Product> findAllByDeletedFalse();
    List<Product> findAllByCategoryId(Long id);
}
