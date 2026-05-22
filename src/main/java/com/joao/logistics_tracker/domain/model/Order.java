package com.joao.logistics_tracker.domain.model;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Product product;
    private TrackingStatus status;
    private Address originAddress;
    private Address destinationAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {}

    public Order(Long id, Product product, TrackingStatus status,
                 Address originAddress, Address destinationAddress,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.product = product;
        this.status = status;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public TrackingStatus getStatus() { return status; }
    public void setStatus(TrackingStatus status) { this.status = status; }

    public Address getOriginAddress() { return originAddress; }
    public void setOriginAddress(Address originAddress) { this.originAddress = originAddress; }

    public Address getDestinationAddress() { return destinationAddress; }
    public void setDestinationAddress(Address destinationAddress) { this.destinationAddress = destinationAddress; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
