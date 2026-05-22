package com.joao.logistics_tracker.domain.model;

import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String name;
    private String sku;
    private String description;
    private LocalDateTime createdAt;

    public Product() {}

    public Product(Long id, String name, String sku,
                   String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
