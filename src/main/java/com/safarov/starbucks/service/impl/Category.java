package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.repository.CategoryRepository;
import com.safarov.starbucks.service.ICategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Category implements ICategory {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public getCategory get(Long id) {
        Optional<com.safarov.starbucks.model.Category> category = categoryRepository.findByDeletedFalseAndId(id);
        if (category.isEmpty()) {
            throw new IdNotFoundException("Category id is empty");
        }
        return modelMapper.map(category, getCategory.class);
    }

    @Override
    public List<getCategory> getAll() {
        List<com.safarov.starbucks.model.Category> categories = categoryRepository.findAllByDeletedFalse();
        return categories.stream().map(category -> modelMapper.map(category,getCategory.class)).collect(Collectors.toList());
    }

    @Override
    public getCategory post(postCategory postCategory) {
        com.safarov.starbucks.model.Category newCategory = modelMapper.map(postCategory, com.safarov.starbucks.model.Category.class);
        return modelMapper.map(categoryRepository.save(newCategory), getCategory.class);
    }

    @Override
    public getCategory put(Long id, putCategory putCategory) {
        Optional<com.safarov.starbucks.model.Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            com.safarov.starbucks.model.Category oldCategory = category.get();
            oldCategory.setName(putCategory.getName());
            return modelMapper.map(categoryRepository.save(oldCategory), getCategory.class);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        com.safarov.starbucks.model.Category varCategory = categoryRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Category not found"));
            varCategory.setDeleted(true);
            categoryRepository.save(varCategory);
    }
}
