package com.safarov.starbucks.dto.customerDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class postCustomer {
    String name;
}
