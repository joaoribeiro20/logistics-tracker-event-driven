package com.joao.logistics_tracker.infra.persistence.entity;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_events")
public class TrackingEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private AddressEntity location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scanned_by_id", nullable = false)
    private UserEntity scannedBy;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Column
    private String notes;

    @PrePersist
    public void prePersist() {
        this.occurredAt = LocalDateTime.now();
    }

    public TrackingEventEntity() {}

    public TrackingEventEntity(Long id, OrderEntity order, TrackingStatus status,
                               AddressEntity location, UserEntity scannedBy,
                               LocalDateTime occurredAt, String notes) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.location = location;
        this.scannedBy = scannedBy;
        this.occurredAt = occurredAt;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }

    public TrackingStatus getStatus() { return status; }
    public void setStatus(TrackingStatus status) { this.status = status; }

    public AddressEntity getLocation() { return location; }
    public void setLocation(AddressEntity location) { this.location = location; }

    public UserEntity getScannedBy() { return scannedBy; }
    public void setScannedBy(UserEntity scannedBy) { this.scannedBy = scannedBy; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
