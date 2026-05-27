package com.joao.logistics_tracker.infra.persistence.mapper;

import com.joao.logistics_tracker.domain.model.Order;
import com.joao.logistics_tracker.infra.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
  private final ProductMapper productMapper;
  private final AddressMapper addressMapper;

  public OrderMapper(ProductMapper productMapper, AddressMapper addressMapper) {
    this.productMapper = productMapper;
    this.addressMapper = addressMapper;
  }

  public OrderEntity toEntity(Order domain) {
    if (domain == null) return null;
    return new OrderEntity(
        domain.getId(),
        productMapper.toEntity(domain.getProduct()),
        domain.getStatus(),
        addressMapper.toEntity(domain.getOriginAddress()),
        addressMapper.toEntity(domain.getDestinationAddress()),
        domain.getCreatedAt(),
        domain.getUpdatedAt());
  }

  public Order toDomain(OrderEntity entity) {
    if (entity == null) return null;
    return new Order(
        entity.getId(),
        productMapper.toDomain(entity.getProduct()),
        entity.getStatus(),
        addressMapper.toDomain(entity.getOriginAddress()),
        addressMapper.toDomain(entity.getDestinationAddress()),
        entity.getCreatedAt(),
        entity.getUpdatedAt());
  }
}
