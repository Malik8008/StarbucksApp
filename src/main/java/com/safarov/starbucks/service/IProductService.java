package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.productDtos.getProduct;
import com.safarov.starbucks.dto.productDtos.postProduct;
import com.safarov.starbucks.dto.productDtos.putProduct;
import com.safarov.starbucks.payload.ApiResponse;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    public ResponseEntity<getProduct> get(Long id);
    public ResponseEntity<List<getProduct>> getAll();
    public ResponseEntity<getProduct> put(Long id, putProduct putProduct);
    public ResponseEntity<ApiResponse<getProduct>> post(postProduct postProduct);
    public ResponseEntity<Void> delete(Long id);
    public ResponseEntity<List<getProduct>> getProductsByCategoryId(Long id);
}
