package com.joao.logistics_tracker.domain.repository;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import com.joao.logistics_tracker.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findByStatus(TrackingStatus status);
    List<Order> findAll();
}
