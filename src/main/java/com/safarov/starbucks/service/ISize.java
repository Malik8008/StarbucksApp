package com.safarov.starbucks.service;

import com.safarov.starbucks.dto.sizeDtos.getSize;
import com.safarov.starbucks.dto.sizeDtos.postSize;
import com.safarov.starbucks.dto.sizeDtos.putSize;

import java.util.List;

public interface ISize {
    public getSize get(Long id);
    public List<getSize> getAll();
    public getSize post(postSize size);
    public getSize put(Long id, putSize size);
    public void delete(Long id);
}
