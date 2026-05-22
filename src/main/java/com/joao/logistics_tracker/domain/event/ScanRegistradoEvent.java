package com.joao.logistics_tracker.domain.event;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;

import java.time.LocalDateTime;

public class ScanRegistradoEvent {

    private final Long orderId;
    private final Long scannedById;
    private final TrackingStatus status;
    private final LocalDateTime occurredAt;

    public ScanRegistradoEvent(Long orderId, Long scannedById,
                               TrackingStatus status, LocalDateTime occurredAt) {
        this.orderId = orderId;
        this.scannedById = scannedById;
        this.status = status;
        this.occurredAt = occurredAt;
    }

    public Long getOrderId() { return orderId; }
    public Long getScannedById() { return scannedById; }
    public TrackingStatus getStatus() { return status; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
}
