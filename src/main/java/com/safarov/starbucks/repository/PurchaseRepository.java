package com.safarov.starbucks.repository;

import com.safarov.starbucks.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByDeletedFalse();
}
