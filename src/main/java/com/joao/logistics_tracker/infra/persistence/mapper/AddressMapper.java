package com.joao.logistics_tracker.infra.persistence.mapper;

import com.joao.logistics_tracker.domain.model.Address;
import com.joao.logistics_tracker.infra.persistence.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity toEntity(Address domain) {
        if (domain == null) return null;
        return new AddressEntity(
                domain.getId(),
                domain.getStreet(),
                domain.getCity(),
                domain.getState(),
                domain.getZipCode(),
                domain.getCountry()
        );
    }

    public Address toDomain(AddressEntity entity) {
        if (entity == null) return null;
        return new Address(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode(),
                entity.getCountry()
        );
    }
}
