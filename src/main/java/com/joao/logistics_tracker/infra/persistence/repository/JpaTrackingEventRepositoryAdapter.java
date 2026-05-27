package com.joao.logistics_tracker.infra.persistence.repository;

import com.joao.logistics_tracker.domain.model.TrackingEvent;
import com.joao.logistics_tracker.domain.repository.TrackingEventRepository;
import com.joao.logistics_tracker.infra.persistence.entity.TrackingEventEntity;
import com.joao.logistics_tracker.infra.persistence.mapper.TrackingEventMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaTrackingEventRepositoryAdapter implements TrackingEventRepository {

  @PersistenceContext private EntityManager entityManager;

  private final TrackingEventMapper mapper;

  public JpaTrackingEventRepositoryAdapter(TrackingEventMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public TrackingEvent save(TrackingEvent event) {
    TrackingEventEntity entity = mapper.toEntity(event);
    if (entity.getId() == null) {
      entityManager.persist(entity);
    } else {
      entity = entityManager.merge(entity);
    }
    return mapper.toDomain(entity);
  }

  @Override
  public List<TrackingEvent> findByOrderId(Long orderId) {
    return entityManager
        .createQuery(
            "SELECT t FROM TrackingEventEntity t WHERE t.order.id = :orderId ORDER BY t.occurredAt ASC",
            TrackingEventEntity.class)
        .setParameter("orderId", orderId)
        .getResultList()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }
}
