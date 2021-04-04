package io.kontur.utils.roleconverter;

import io.kontur.entity.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
  @Override
  public String convertToDatabaseColumn(Role attribute) {
    return attribute.getValue();
  }

  @Override
  public Role convertToEntityAttribute(String dbData) {
    return Role.getRole(dbData);
  }
}

