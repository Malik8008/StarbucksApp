package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.categoryDtos.getCategory;
import com.safarov.starbucks.dto.productDtos.getProduct;
import com.safarov.starbucks.dto.productDtos.postProduct;
import com.safarov.starbucks.dto.productDtos.putProduct;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.model.Category;
import com.safarov.starbucks.model.Product;
import com.safarov.starbucks.model.Size;
import com.safarov.starbucks.payload.ApiResponse;
import com.safarov.starbucks.repository.CategoryRepository;
import com.safarov.starbucks.repository.ProductRepository;
import com.safarov.starbucks.repository.SizeRepository;
import com.safarov.starbucks.service.IDiscountService;
import com.safarov.starbucks.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService, IDiscountService {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<getProduct> get(Long id) {
        Product product = productRepository.findByIdAndDeleted(id, false);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(modelMapper.map(product, getProduct.class), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<getProduct>> getAll() {
        List<Product> products = productRepository.findAllByDeletedFalse();
        return ResponseEntity.ok(products.stream().map(product -> modelMapper.map(product, getProduct.class)).toList()
        );
    }


    @Override
    public ResponseEntity<ApiResponse<getProduct>> post(postProduct postProduct) {
        Category category = categoryRepository.findByDeletedFalseAndId(postProduct.getCategory_id()).orElseThrow(() -> new IdNotFoundException("Category id not found: " + postProduct.getCategory_id()));
        Size size = sizeRepository.findByDeletedFalseAndId(postProduct.getSize_id()).orElseThrow(() -> new IdNotFoundException("Size id not found: " + postProduct.getSize_id()));
        Product product = modelMapper.map(postProduct, Product.class);
        product.setCategory(category);
        product.setSize(size);
        getProduct responseDto = modelMapper.map(productRepository.save(product), getProduct.class);
        ApiResponse<getProduct> response = new ApiResponse<>("Product added successfully.", responseDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<getProduct> put(Long id, putProduct putProduct) {
        Category category = categoryRepository.findByDeletedFalseAndId(putProduct.getCategory_id()).orElseThrow(() -> new IdNotFoundException("Category id not found: " + putProduct.getCategory_id()));
        Size size = sizeRepository.findByDeletedFalseAndId(putProduct.getSize_id()).orElseThrow(() -> new IdNotFoundException("Size id not found: " + putProduct.getSize_id()));
        Product oldProduct = productRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Product id is not found"));
            Product product = modelMapper.map(putProduct, Product.class);
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
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            productRepository.save(product);
            return ResponseEntity.ok().build();
        }
        throw new IdNotFoundException("This id does not exist: " + id);
    }

    @Override
    public ResponseEntity<List<getProduct>> getProductsByCategoryId(Long id) {
        List<Product> products = productRepository.findAllByCategoryId(id);
        List<getProduct> getProducts = products.stream().map(product -> modelMapper.map(product, getProduct.class)).toList();
        if (getProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getProducts);
    }

    @Override
    public ResponseEntity<List<getProduct>> addDiscountForCategory(Long categoryId, int discount) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        List<getProduct> getProducts = products.stream().map(product -> {
            double newSalePrice = product.getSalePrice() * (1 - discount / 100.0);
            product.setSalePrice(newSalePrice);
            product.setDiscount(discount);
            productRepository.save(product);
            return modelMapper.map(product, getProduct.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(getProducts);
    }
}
