package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.dto.sizeDtos.postSize;
import com.safarov.starbucks.dto.sizeDtos.putSize;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.model.Size;
import com.safarov.starbucks.repository.SizeRepository;
import com.safarov.starbucks.service.ISizeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeService implements ISizeService {

    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    @Override
    public getSize get(Long id) {
        Optional<Size> size = sizeRepository.findByDeletedFalseAndId(id);
        if (size.isEmpty()) {
            throw new IdNotFoundException("Size id is empty");
        }
        return modelMapper.map(size, getSize.class);
    }

    @Override
    public List<getSize> getAll() {
        List<Size> sizes = sizeRepository.findAllByDeletedFalse();
        return sizes.stream().map(size -> modelMapper.map(size, getSize.class)).collect(Collectors.toList());
    }

    @Override
    public getSize post(postSize size) {
        Size newSize = modelMapper.map(size, Size.class);
        return modelMapper.map(sizeRepository.save(newSize), getSize.class);
    }

    @Override
    public getSize put(Long id, putSize size) {
        Size oldsize = sizeRepository.findById(id).orElse(null);
        if (oldsize == null) {
            return null;
        }
        oldsize.setId(id);
        oldsize.setName(size.getName());
        return modelMapper.map(sizeRepository.save(oldsize), getSize.class);
    }

    @Override
    public void delete(Long id) {
        Size oldsize = sizeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Size id not found"));
        oldsize.setDeleted(true);
        sizeRepository.save(oldsize);
    }
}
