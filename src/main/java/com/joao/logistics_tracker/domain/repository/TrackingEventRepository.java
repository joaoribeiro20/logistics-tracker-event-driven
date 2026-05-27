package com.joao.logistics_tracker.domain.repository;

import com.joao.logistics_tracker.domain.model.TrackingEvent;
import java.util.List;

public interface TrackingEventRepository {
  TrackingEvent save(TrackingEvent event);

  List<TrackingEvent> findByOrderId(Long orderId);
}
