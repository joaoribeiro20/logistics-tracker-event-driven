package com.joao.logistics_tracker.application.usecase;

import com.joao.logistics_tracker.application.dto.RegisterScanRequest;
import com.joao.logistics_tracker.application.dto.RegisterScanResponse;
import com.joao.logistics_tracker.domain.event.ScanRegistradoEvent;
import com.joao.logistics_tracker.domain.model.Address;
import com.joao.logistics_tracker.domain.model.Order;
import com.joao.logistics_tracker.domain.model.TrackingEvent;
import com.joao.logistics_tracker.domain.model.User;
import com.joao.logistics_tracker.domain.repository.OrderRepository;
import com.joao.logistics_tracker.domain.repository.TrackingEventRepository;
import java.time.LocalDateTime;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RegisterScanUseCase {

  private final TrackingEventRepository trackingEventRepository;
  private final OrderRepository orderRepository;
  private final ApplicationEventPublisher publisher;

  public RegisterScanUseCase(
      TrackingEventRepository trackingEventRepository,
      OrderRepository orderRepository,
      ApplicationEventPublisher publisher) {
    this.trackingEventRepository = trackingEventRepository;
    this.orderRepository = orderRepository;
    this.publisher = publisher;
  }

  public RegisterScanResponse execute(RegisterScanRequest request) {

    // 1. busca o pedido
    Order order =
        orderRepository
            .findById(request.getOrderId())
            .orElseThrow(
                () ->
                    new IllegalArgumentException("Pedido não encontrado: " + request.getOrderId()));

    // 2. monta quem escaneou (só com o id por enquanto)
    User scannedBy = new User();
    scannedBy.setId(request.getScannedById());

    // 3. monta o local do scan (só com o id por enquanto)
    Address location = new Address();
    location.setId(request.getLocationId());

    // 4. cria o evento de rastreamento
    TrackingEvent trackingEvent =
        new TrackingEvent(
            null,
            order,
            request.getStatus(),
            location,
            scannedBy,
            LocalDateTime.now(),
            request.getNotes());

    // 5. persiste
    TrackingEvent saved = trackingEventRepository.save(trackingEvent);

    // 6. atualiza o status do pedido
    order.setStatus(request.getStatus());
    orderRepository.save(order);

    // 7. dispara o evento interno — Pub-Sub
    publisher.publishEvent(
        new ScanRegistradoEvent(
            order.getId(), request.getScannedById(), request.getStatus(), saved.getOccurredAt()));

    // 8. retorna resposta
    return new RegisterScanResponse(
        saved.getId(), order.getId(), saved.getStatus(), saved.getOccurredAt());
  }
}
