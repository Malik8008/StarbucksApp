package com.safarov.starbucks.repository;

import com.safarov.starbucks.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    Optional<Size> findByDeletedFalseAndId(Long id);
    List<Size> findAllByDeletedFalse();

}
