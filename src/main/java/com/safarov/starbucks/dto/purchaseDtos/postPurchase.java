package com.safarov.starbucks.dto.purchaseDtos;

import com.safarov.starbucks.dto.PurchaseItemDtos.postPurchaseItem;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class postPurchase {
    BigDecimal totalPrice;
    LocalDateTime orderDate;
    String description;
    Long customerId;
    List<postPurchaseItem> purchaseItems;
}
