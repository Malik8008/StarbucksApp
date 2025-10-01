package com.safarov.starbucks.dto.PurchaseItemDtos;

import com.safarov.starbucks.dto.productDtos.getProduct;
import lombok.Data;

@Data
public class postPurchaseItem {
    int count;
    Long product_id;
}
