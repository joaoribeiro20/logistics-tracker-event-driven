package com.joao.logistics_tracker.domain.repository;

import com.joao.logistics_tracker.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    void deleteById(Long id);
}
