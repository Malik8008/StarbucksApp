package com.safarov.starbucks.dto.purchaseDtos;

import com.safarov.starbucks.dto.PurchaseItemDtos.getPurchaseItem;
import com.safarov.starbucks.dto.customerDtos.getCustomer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class getPurchase {
    Long id;
    String description;
    BigDecimal totalPrice;
    LocalDateTime orderDate;
    getCustomer customer;
    List<getPurchaseItem> purchaseItems;
}
