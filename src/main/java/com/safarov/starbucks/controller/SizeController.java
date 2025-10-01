package com.safarov.starbucks.controller;

import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.dto.sizeDtos.postSize;
import com.safarov.starbucks.dto.sizeDtos.putSize;
import com.safarov.starbucks.service.impl.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
public class SizeController {

    private final Size sizeService;

    @GetMapping("{id}")
    public ResponseEntity<getSize> get(@PathVariable Long id) {
        getSize size=sizeService.get(id);
        if (size==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(size);
    }

    @GetMapping
    public ResponseEntity<List<getSize>> getAllSizes() {
        return ResponseEntity.ok(sizeService.getAll());
    }

    @PostMapping
    public ResponseEntity<getSize> post(@RequestBody postSize size) {
        return ResponseEntity.ok(sizeService.post(size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<getSize> put(@PathVariable Long id,@RequestBody putSize size) {
        return ResponseEntity.ok(sizeService.put(id, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<getSize> delete(@PathVariable Long id) {
        sizeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
