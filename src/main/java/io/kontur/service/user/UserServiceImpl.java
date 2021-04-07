package io.kontur.service.user;

import io.kontur.entity.User;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.UserCrudRepository;
import io.kontur.service.dto.UserDto;
import io.kontur.utils.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private static final String ENTITY_NAME = "User";
  private final UserCrudRepository repository;
  private final UserMapper userMapper;

  @Override
  public UserDto create(UserDto dto) {
    log.info("add user");
    Optional<User> userOptional = repository.findUserByLogin(dto.getLogin());
    if (userOptional.isEmpty()) {
      User user = userMapper.toEntity(dto);
      user.setCreateDate(ZonedDateTime.now());
      user.setLastUpdateDate(ZonedDateTime.now());
      return userMapper.toDto(repository.save(user));
    }
    return userMapper.toDto(userOptional.get());
  }

  @Override
  public UserDto read(int id) {
    log.info("find user {}", id);
    Optional<User> optionalUser = repository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return userMapper.toDto(optionalUser.get());
  }

  @Override
  public UserDto delete(int id) {
    log.info("delete user {}", id);
    Optional<User> userOptional = repository.findById(id);
    if (userOptional.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return userMapper.toDto(userOptional.get());
  }

  @Override
  public List<UserDto> allUsers() {
    List<User> users = (List<User>) repository.findAll();
    if (users.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, 0L);
    }
    return userMapper.toDtoList(users);
  }
}
