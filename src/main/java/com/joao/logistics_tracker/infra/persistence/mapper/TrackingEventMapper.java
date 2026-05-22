package com.joao.logistics_tracker.infra.persistence.mapper;

import com.joao.logistics_tracker.domain.model.TrackingEvent;
import com.joao.logistics_tracker.infra.persistence.entity.TrackingEventEntity;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventMapper {
    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public TrackingEventMapper(OrderMapper orderMapper,
                               AddressMapper addressMapper,
                               UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    public TrackingEventEntity toEntity(TrackingEvent domain) {
        if (domain == null) return null;
        return new TrackingEventEntity(
                domain.getId(),
                orderMapper.toEntity(domain.getOrder()),
                domain.getStatus(),
                addressMapper.toEntity(domain.getLocation()),
                userMapper.toEntity(domain.getScannedBy()),
                domain.getOccurredAt(),
                domain.getNotes()
        );
    }

    public TrackingEvent toDomain(TrackingEventEntity entity) {
        if (entity == null) return null;
        return new TrackingEvent(
                entity.getId(),
                orderMapper.toDomain(entity.getOrder()),
                entity.getStatus(),
                addressMapper.toDomain(entity.getLocation()),
                userMapper.toDomain(entity.getScannedBy()),
                entity.getOccurredAt(),
                entity.getNotes()
        );
    }
}
