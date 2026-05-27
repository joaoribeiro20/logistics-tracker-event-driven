package com.joao.logistics_tracker.infra.persistence.entity;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TrackingStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_address_id", nullable = false)
  private AddressEntity originAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_address_id", nullable = false)
  private AddressEntity destinationAddress;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
    this.status = TrackingStatus.DISPATCHED;
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public OrderEntity() {}

  public OrderEntity(
      Long id,
      ProductEntity product,
      TrackingStatus status,
      AddressEntity originAddress,
      AddressEntity destinationAddress,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.product = product;
    this.status = status;
    this.originAddress = originAddress;
    this.destinationAddress = destinationAddress;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public TrackingStatus getStatus() {
    return status;
  }

  public void setStatus(TrackingStatus status) {
    this.status = status;
  }

  public AddressEntity getOriginAddress() {
    return originAddress;
  }

  public void setOriginAddress(AddressEntity originAddress) {
    this.originAddress = originAddress;
  }

  public AddressEntity getDestinationAddress() {
    return destinationAddress;
  }

  public void setDestinationAddress(AddressEntity destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
