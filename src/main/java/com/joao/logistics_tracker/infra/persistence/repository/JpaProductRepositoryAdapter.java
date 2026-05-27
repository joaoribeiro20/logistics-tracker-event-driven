package com.joao.logistics_tracker.infra.persistence.repository;

import com.joao.logistics_tracker.domain.model.Product;
import com.joao.logistics_tracker.domain.repository.ProductRepository;
import com.joao.logistics_tracker.infra.persistence.entity.ProductEntity;
import com.joao.logistics_tracker.infra.persistence.mapper.ProductMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaProductRepositoryAdapter implements ProductRepository {

  @PersistenceContext private EntityManager entityManager;

  private final ProductMapper mapper;

  public JpaProductRepositoryAdapter(ProductMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Product save(Product product) {
    ProductEntity entity = mapper.toEntity(product);
    if (entity.getId() == null) {
      entityManager.persist(entity);
    } else {
      entity = entityManager.merge(entity);
    }
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<Product> findById(Long id) {
    ProductEntity entity = entityManager.find(ProductEntity.class, id);
    return Optional.ofNullable(mapper.toDomain(entity));
  }

  @Override
  public Optional<Product> findBySku(String sku) {
    List<ProductEntity> result =
        entityManager
            .createQuery("SELECT p FROM ProductEntity p WHERE p.sku = :sku", ProductEntity.class)
            .setParameter("sku", sku)
            .getResultList();
    return result.stream().findFirst().map(mapper::toDomain);
  }

  @Override
  public List<Product> findAll() {
    return entityManager
        .createQuery("SELECT p FROM ProductEntity p", ProductEntity.class)
        .getResultList()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    ProductEntity entity = entityManager.find(ProductEntity.class, id);
    if (entity != null) {
      entityManager.remove(entity);
    }
  }
}
