package com.safarov.starbucks.dto.PurchaseItemDtos;

import com.safarov.starbucks.dto.productDtos.productOnlyName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class getPurchaseItem {
    Long id;
    int count;
    productOnlyName productName;
}
