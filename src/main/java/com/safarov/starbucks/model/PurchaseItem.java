package com.safarov.starbucks.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int count;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
