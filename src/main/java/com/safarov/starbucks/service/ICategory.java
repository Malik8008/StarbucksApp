package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;

import java.util.List;

public interface ICategory {
    public getCategory get(Long id);
    public List<getCategory> getAll();
    public getCategory post(postCategory postCategory);
    public getCategory put(Long id, putCategory putCategory);
    public void delete(Long id);
}