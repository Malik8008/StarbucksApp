package com.safarov.starbucks.dto.productDtos;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.sizeDtos.getSize;
import lombok.Data;

@Data
public class getProduct {
    Long id;
    String name;
    String description;
    Double salePrice;
    int discount;
    getSize size;
    getCategory category;
}
