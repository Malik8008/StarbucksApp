package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.dto.sizeDtos.postSize;
import com.safarov.starbucks.dto.sizeDtos.putSize;
import com.safarov.starbucks.service.impl.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
public class SizeController {

    private final SizeService service;

    @GetMapping("{id}")
    public ResponseEntity<getSize> get(@PathVariable Long id) {
        getSize size=service.get(id);
        if (size==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(size);
    }

    @PostMapping
    public ResponseEntity<getSize> post(@RequestBody postSize size) {
        return ResponseEntity.ok(service.post(size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<getSize> put(@PathVariable Long id,@RequestBody putSize size) {
        return ResponseEntity.ok(service.put(id, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<getSize> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
