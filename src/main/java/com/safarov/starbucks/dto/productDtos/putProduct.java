package com.safarov.starbucks.dto.productDtos;

import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.model.Size;
import lombok.Data;

@Data
public class putProduct {
    String name;
    String description;
    Double salePrice;
    Double costPrice;
    int discount;
    Long size_id;
    Long category_id;
}
