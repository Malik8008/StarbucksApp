package com.safarov.starbucks.dto.PurchaseItemDtos;

import com.safarov.starbucks.dto.productDtos.getProductOnlyName;
import lombok.Data;

@Data
public class getPurchaseItem {
    Long id;
    int count;
    getProductOnlyName name;
}
