package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.PurchaseItemDtos.postPurchaseItem;
import com.safarov.starbucks.dto.purchaseDtos.getPurchase;
import com.safarov.starbucks.dto.purchaseDtos.postPurchase;
import com.safarov.starbucks.dto.purchaseDtos.putPurchase;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.model.Purchase;
import com.safarov.starbucks.model.PurchaseItem;
import com.safarov.starbucks.model.Product;
import com.safarov.starbucks.repository.CustomerRepository;
import com.safarov.starbucks.repository.PurchaseRepository;
import com.safarov.starbucks.repository.ProductRepository;
import com.safarov.starbucks.service.IPurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService implements IPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<getPurchase> getPurchase(Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(()->new IdNotFoundException("Purchase not found"));
        return ResponseEntity.ok(modelMapper.map(purchase,getPurchase.class));
    }

    @Override
    public ResponseEntity<List<getPurchase>> getAllPurchase() {
        List<Purchase> purchases = purchaseRepository.findAllByDeletedFalse();
        if(purchases.isEmpty()){
            throw new IdNotFoundException("Purchase is empty");
        }
        return ResponseEntity.ok(purchases.stream().map(purchase-> modelMapper.map(purchase,getPurchase.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<getPurchase> saveOrder(postPurchase orderDto) {
        Purchase purchase = modelMapper.map(orderDto, Purchase.class);
        purchase.setCustomer(customerRepository.findById(orderDto.getCustomerId()).orElseThrow(() -> new IdNotFoundException("Customer not found")));
        List<PurchaseItem> itemList = new ArrayList<>();
        for (postPurchaseItem itemDto : orderDto.getPurchaseItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            PurchaseItem item = new PurchaseItem();
            item.setProduct(product);
            item.setCount(itemDto.getCount());
            item.setPurchase(purchase); // əlaqəni qur

            itemList.add(item);
        }
        purchase.setItems(itemList);
        Purchase orderSaved = purchaseRepository.save(purchase);
        return ResponseEntity.ok(modelMapper.map(orderSaved, getPurchase.class));
    }

    @Override
    public ResponseEntity<getPurchase> updatePurchase(Long id, putPurchase purchaseDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> deletePurchase(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            purchaseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
