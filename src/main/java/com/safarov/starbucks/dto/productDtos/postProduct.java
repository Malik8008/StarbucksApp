package com.safarov.starbucks.dto.productDtos;


import lombok.Data;

@Data
public class postProduct {
    String name;
    String description;
    Double costPrice;
    Double salePrice;
    int discount;
    Long size_id;
    Long category_id;

}
