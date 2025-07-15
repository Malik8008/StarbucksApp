package com.safarov.starbucks.dto.customerDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class getCustomer {
    Long id;
    String name;
}
