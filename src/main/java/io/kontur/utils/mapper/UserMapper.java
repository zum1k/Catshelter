package io.kontur.utils.mapper;

import io.kontur.entity.User;
import io.kontur.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", source = "dto.id")
  User toEntity(UserDto dto);

  @Mapping(target = "id", source = "user.id")
  UserDto toDto(User user);

  List<UserDto> toDtoList(List<User> users);
}
