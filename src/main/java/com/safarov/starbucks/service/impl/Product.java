package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.productDtos.getProduct;
import com.safarov.starbucks.dto.productDtos.postProduct;
import com.safarov.starbucks.dto.productDtos.putProduct;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.model.Size;
import com.safarov.starbucks.payload.ApiResponse;
import com.safarov.starbucks.repository.CategoryRepository;
import com.safarov.starbucks.repository.ProductRepository;
import com.safarov.starbucks.repository.SizeRepository;
import com.safarov.starbucks.service.IDiscount;
import com.safarov.starbucks.service.IProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Product implements IProduct, IDiscount {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<getProduct> get(Long id) {
        com.safarov.starbucks.model.Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(()-> new IdNotFoundException("Product with id " + id + " not found"));
        return new ResponseEntity<>(modelMapper.map(product, getProduct.class), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<getProduct>> getAll() {
        List<com.safarov.starbucks.model.Product> products = productRepository.findAllByDeletedFalse();
        return ResponseEntity.ok(products.stream().map(product -> modelMapper.map(product, getProduct.class)).toList()
        );
    }


    @Override
    public ResponseEntity<ApiResponse<getProduct>> post(postProduct postProduct) {
        Category category = categoryRepository.findByDeletedFalseAndId(postProduct.getCategory_id()).orElseThrow(() -> new IdNotFoundException("Category not found: " + postProduct.getCategory_id()));
        Size size = sizeRepository.findByDeletedFalseAndId(postProduct.getSize_id()).orElseThrow(() -> new IdNotFoundException("Size not found: " + postProduct.getSize_id()));
        com.safarov.starbucks.model.Product product = modelMapper.map(postProduct, com.safarov.starbucks.model.Product.class);
        product.setCategory(category);
        product.setSize(size);
        getProduct responseDto = modelMapper.map(productRepository.save(product), getProduct.class);
        ApiResponse<getProduct> response = new ApiResponse<>("Product added successfully.", responseDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<getProduct> put(Long id, putProduct putProduct) {
        Category category = categoryRepository.findByDeletedFalseAndId(putProduct.getCategory_id()).orElseThrow(() -> new IdNotFoundException("Category not found: " + putProduct.getCategory_id()));
        Size size = sizeRepository.findByDeletedFalseAndId(putProduct.getSize_id()).orElseThrow(() -> new IdNotFoundException("Size not found: " + putProduct.getSize_id()));
        com.safarov.starbucks.model.Product product = productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Product id is not found"));
        product.setId(id);
        product.setCategory(category);
        product.setSize(size);
        product.setName(putProduct.getName());
        product.setDescription(putProduct.getDescription());
        product.setCostPrice(putProduct.getCostPrice());
        product.setSalePrice(putProduct.getSalePrice());
        return ResponseEntity.ok(modelMapper.map(productRepository.save(product), getProduct.class));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        com.safarov.starbucks.model.Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            productRepository.save(product);
            return ResponseEntity.ok().build();
        }
        throw new IdNotFoundException("This id does not exist: " + id);
    }

    @Override
    public ResponseEntity<List<getProduct>> getProductsByCategoryId(Long id) {
        List<com.safarov.starbucks.model.Product> products = productRepository.findAllByCategoryId(id);
        List<getProduct> getProducts = products.stream().map(product -> modelMapper.map(product, getProduct.class)).toList();
        if (getProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getProducts);
    }

    @Override
    public ResponseEntity<List<getProduct>> addDiscountForCategory(Long categoryId, int discount) {
        List<com.safarov.starbucks.model.Product> products = productRepository.findAllByCategoryId(categoryId);
        List<getProduct> getProducts = products.stream().map(product -> {
            if (product.getDiscount() == 0) {
                double newSalePrice = product.getSalePrice() * (1 - discount / 100.0);
                product.setSalePrice(newSalePrice);
                product.setDiscount(discount);
                productRepository.save(product);
                return modelMapper.map(product, getProduct.class);
            } else {
                return modelMapper.map(product, getProduct.class);
            }
        }).collect(Collectors.toList());
        return ResponseEntity.ok(getProducts);
    }
}
