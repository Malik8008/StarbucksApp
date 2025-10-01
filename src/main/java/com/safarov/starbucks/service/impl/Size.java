package com.safarov.starbucks.service.impl;

import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.dto.sizeDtos.postSize;
import com.safarov.starbucks.dto.sizeDtos.putSize;
import com.safarov.starbucks.exception.IdNotFoundException;
import com.safarov.starbucks.repository.SizeRepository;
import com.safarov.starbucks.service.ISize;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Size implements ISize {

    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    @Override
    public getSize get(Long id) {
        Optional<com.safarov.starbucks.model.Size> size = sizeRepository.findByDeletedFalseAndId(id);
        if (size.isEmpty()) {
            throw new IdNotFoundException("Size id is empty");
        }
        return modelMapper.map(size, getSize.class);
    }

    @Override
    public List<getSize> getAll() {
        List<com.safarov.starbucks.model.Size> sizes = sizeRepository.findAllByDeletedFalse();
        return sizes.stream().map(size -> modelMapper.map(size, getSize.class)).collect(Collectors.toList());
    }

    @Override
    public getSize post(postSize size) {
        com.safarov.starbucks.model.Size newSize = modelMapper.map(size, com.safarov.starbucks.model.Size.class);
        return modelMapper.map(sizeRepository.save(newSize), getSize.class);
    }

    @Override
    public getSize put(Long id, putSize size) {
        com.safarov.starbucks.model.Size oldsize = sizeRepository.findById(id).orElse(null);
        if (oldsize == null) {
            return null;
        }
        oldsize.setId(id);
        oldsize.setName(size.getName());
        return modelMapper.map(sizeRepository.save(oldsize), getSize.class);
    }

    @Override
    public void delete(Long id) {
        com.safarov.starbucks.model.Size oldsize = sizeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Size id not found"));
        oldsize.setDeleted(true);
        sizeRepository.save(oldsize);
    }
}
