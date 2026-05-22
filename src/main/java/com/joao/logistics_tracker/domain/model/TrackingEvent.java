package com.joao.logistics_tracker.domain.model;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;
import java.time.LocalDateTime;

public class TrackingEvent {
    private Long id;
    private Order order;
    private TrackingStatus status;
    private Address location;
    private User scannedBy;
    private LocalDateTime occurredAt;
    private String notes;

    public TrackingEvent() {}

    public TrackingEvent(Long id, Order order, TrackingStatus status,
                         Address location, User scannedBy,
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

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public TrackingStatus getStatus() { return status; }
    public void setStatus(TrackingStatus status) { this.status = status; }

    public Address getLocation() { return location; }
    public void setLocation(Address location) { this.location = location; }

    public User getScannedBy() { return scannedBy; }
    public void setScannedBy(User scannedBy) { this.scannedBy = scannedBy; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
