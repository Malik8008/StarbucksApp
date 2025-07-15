package com.safarov.starbucks.repository;

import com.safarov.starbucks.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByDeletedFalseAndId(Long id);
    List<Category> findAllByDeletedFalse();
}
