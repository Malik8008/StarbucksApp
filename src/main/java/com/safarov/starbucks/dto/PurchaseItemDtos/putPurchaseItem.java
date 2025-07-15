package com.safarov.starbucks.dto.PurchaseItemDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class putPurchaseItem {
    int count;
    Long productId;
}
