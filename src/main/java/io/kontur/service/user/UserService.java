package io.kontur.service.user;

import io.kontur.service.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto create(UserDto dto);

  UserDto read(int id);

  UserDto delete(int id);

  List<UserDto> allUsers();
}
