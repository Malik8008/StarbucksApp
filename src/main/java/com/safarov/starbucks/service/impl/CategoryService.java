package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.categoryDtos.postCategory;
import com.safarov.starbucks.dto.categoryDtos.putCategory;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.repository.CategoryRepository;
import com.safarov.starbucks.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public getCategory get(Long id) {
        Optional<Category> category = categoryRepository.findByDeletedFalseAndId(id);
        if (category.isEmpty()) {
            throw new IdNotFoundException("Category id is empty");
        }
        return modelMapper.map(category, getCategory.class);
    }

    @Override
    public List<getCategory> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category,getCategory.class)).collect(Collectors.toList());
    }

    @Override
    public getCategory post(postCategory postCategory) {
        Category newCategory = modelMapper.map(postCategory, Category.class);
        return modelMapper.map(categoryRepository.save(newCategory), getCategory.class);
    }

    @Override
    public getCategory put(Long id, putCategory putCategory) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category oldCategory = category.get();
            oldCategory.setName(putCategory.getName());
            return modelMapper.map(categoryRepository.save(oldCategory), getCategory.class);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Category varCategory = categoryRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Category not found"));
            varCategory.setDeleted(true);
            categoryRepository.save(varCategory);
    }
}
