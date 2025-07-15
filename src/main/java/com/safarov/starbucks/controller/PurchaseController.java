package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.purchaseDtos.getPurchase;
import com.safarov.starbucks.dto.purchaseDtos.postPurchase;
import com.safarov.starbucks.model.Purchase;
import com.safarov.starbucks.service.impl.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<getPurchase> getPurchase(@PathVariable Long id) {
        return purchaseService.getPurchase(id);
    }

    @GetMapping
    public ResponseEntity<List<getPurchase>> getPurchases() {
        return purchaseService.getAllPurchase();
    }


    @PostMapping
    public ResponseEntity<getPurchase> createOrder(@RequestBody postPurchase purchaseDto) {
        return purchaseService.saveOrder(purchaseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
           return   purchaseService.deletePurchase(id);
    }
}
