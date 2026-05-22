package com.joao.logistics_tracker.application.dto;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;

import java.time.LocalDateTime;

public class RegisterScanResponse {

    private Long trackingEventId;
    private Long orderId;
    private TrackingStatus status;
    private LocalDateTime occurredAt;

    public RegisterScanResponse() {}

    public RegisterScanResponse(Long trackingEventId, Long orderId,
                                TrackingStatus status, LocalDateTime occurredAt) {
        this.trackingEventId = trackingEventId;
        this.orderId = orderId;
        this.status = status;
        this.occurredAt = occurredAt;
    }

    public Long getTrackingEventId() { return trackingEventId; }
    public void setTrackingEventId(Long trackingEventId) { this.trackingEventId = trackingEventId; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public TrackingStatus getStatus() { return status; }
    public void setStatus(TrackingStatus status) { this.status = status; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }
}
