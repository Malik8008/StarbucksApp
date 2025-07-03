package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;
import com.safarov.starbucks.model.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ICategoryService {
    public getCategory get(Long id);
    public List<getCategory> getAll();
    public getCategory post(postCategory postCategory);
    public getCategory put(Long id, putCategory putCategory);
    public void delete(Long id);
}