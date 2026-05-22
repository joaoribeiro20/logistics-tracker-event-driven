package com.joao.logistics_tracker.infra.persistence.mapper;

import com.joao.logistics_tracker.domain.model.Product;
import com.joao.logistics_tracker.infra.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity toEntity(Product domain) {
        if (domain == null) return null;
        return new ProductEntity(
                domain.getId(),
                domain.getName(),
                domain.getSku(),
                domain.getDescription(),
                domain.getCreatedAt()
        );
    }

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getSku(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }
}
