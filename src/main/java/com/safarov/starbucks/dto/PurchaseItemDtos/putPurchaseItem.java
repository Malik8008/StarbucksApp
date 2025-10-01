package com.safarov.starbucks.dto.PurchaseItemDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
public class putPurchaseItem {
    int count;
    Long product_id;
}
