package com.joao.logistics_tracker.infra.persistence.mapper;

import com.joao.logistics_tracker.domain.model.User;
import com.joao.logistics_tracker.infra.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserEntity toEntity(User domain) {
    if (domain == null) return null;
    return new UserEntity(
        domain.getId(),
        domain.getName(),
        domain.getEmail(),
        domain.getRole(),
        domain.getCreatedAt());
  }

  public User toDomain(UserEntity entity) {
    if (entity == null) return null;
    return new User(
        entity.getId(),
        entity.getName(),
        entity.getEmail(),
        entity.getRole(),
        entity.getCreatedAt());
  }
}
