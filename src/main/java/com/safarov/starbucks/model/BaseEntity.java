package com.safarov.starbucks.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
    private boolean deleted;
    private boolean isDiscounted;
}
