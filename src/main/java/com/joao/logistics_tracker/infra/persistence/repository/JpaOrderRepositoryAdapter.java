package com.joao.logistics_tracker.infra.persistence.repository;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import com.joao.logistics_tracker.domain.model.Order;
import com.joao.logistics_tracker.domain.repository.OrderRepository;
import com.joao.logistics_tracker.infra.persistence.entity.OrderEntity;
import com.joao.logistics_tracker.infra.persistence.mapper.OrderMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaOrderRepositoryAdapter implements OrderRepository {

  @PersistenceContext private EntityManager entityManager;

  private final OrderMapper mapper;

  public JpaOrderRepositoryAdapter(OrderMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Order save(Order order) {
    OrderEntity entity = mapper.toEntity(order);
    if (entity.getId() == null) {
      entityManager.persist(entity);
    } else {
      entity = entityManager.merge(entity);
    }
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<Order> findById(Long id) {
    OrderEntity entity = entityManager.find(OrderEntity.class, id);
    return Optional.ofNullable(mapper.toDomain(entity));
  }

  @Override
  public List<Order> findByStatus(TrackingStatus status) {
    return entityManager
        .createQuery("SELECT o FROM OrderEntity o WHERE o.status = :status", OrderEntity.class)
        .setParameter("status", status)
        .getResultList()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  @Override
  public List<Order> findAll() {
    return entityManager
        .createQuery("SELECT o FROM OrderEntity o", OrderEntity.class)
        .getResultList()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }
}
