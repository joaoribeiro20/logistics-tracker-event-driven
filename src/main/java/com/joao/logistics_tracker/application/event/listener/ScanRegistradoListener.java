package com.joao.logistics_tracker.application.event.listener;

import com.joao.logistics_tracker.domain.event.ScanRegistradoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ScanRegistradoListener {

    @Async
    @EventListener
    public void handle(ScanRegistradoEvent event) {
        log(event);
        notify(event);
    }

    private void log(ScanRegistradoEvent event) {
        // LogUseCase vai entrar aqui depois
        System.out.println("[LOG] Scan registrado — pedido: " + event.getOrderId()
                + " | status: " + event.getStatus()
                + " | em: " + event.getOccurredAt());
    }

    private void notify(ScanRegistradoEvent event) {
        // NotificationUseCase vai entrar aqui depois
        System.out.println("[NOTIFY] Notificando cliente do pedido: " + event.getOrderId()
                + " | novo status: " + event.getStatus());
    }
}