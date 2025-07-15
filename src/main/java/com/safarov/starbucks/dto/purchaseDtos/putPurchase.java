package com.safarov.starbucks.dto.purchaseDtos;

import com.safarov.starbucks.dto.PurchaseItemDtos.putPurchaseItem;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class putPurchase {
    String description;
    BigDecimal price;
    LocalDateTime orderDate;
    Long customerId;
    List<putPurchaseItem> purchaseItems;
}
