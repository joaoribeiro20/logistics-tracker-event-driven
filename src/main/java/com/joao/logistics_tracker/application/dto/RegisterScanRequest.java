package com.joao.logistics_tracker.application.dto;

import com.joao.logistics_tracker.domain.enums.TrackingStatus;

public class RegisterScanRequest {

  private Long orderId;
  private Long scannedById;
  private Long locationId;
  private TrackingStatus status;
  private String notes;

  public RegisterScanRequest() {}

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getScannedById() {
    return scannedById;
  }

  public void setScannedById(Long scannedById) {
    this.scannedById = scannedById;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public TrackingStatus getStatus() {
    return status;
  }

  public void setStatus(TrackingStatus status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
