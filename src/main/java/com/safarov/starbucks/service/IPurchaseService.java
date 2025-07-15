package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.purchaseDtos.getPurchase;
import com.safarov.starbucks.dto.purchaseDtos.postPurchase;
import com.safarov.starbucks.dto.purchaseDtos.putPurchase;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPurchaseService {
    ResponseEntity<getPurchase> getPurchase(Long id);
    ResponseEntity<List<getPurchase>> getAllPurchase();
    ResponseEntity<getPurchase> saveOrder(postPurchase purchaseDto);
    ResponseEntity<getPurchase> updatePurchase(Long id, putPurchase purchaseDto);
    ResponseEntity<String> deletePurchase(Long id);
}
