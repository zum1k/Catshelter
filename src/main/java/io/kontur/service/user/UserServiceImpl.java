package io.kontur.service.user;

import io.kontur.entity.User;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.Repository;
import io.kontur.repository.specification.CriteriaSpecification;
import io.kontur.repository.specification.UserByLoginSpecification;
import io.kontur.service.dto.UserDto;
import io.kontur.utils.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private static final String ENTITY_NAME = "User";
  private final Repository<User> repository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserDto create(UserDto dto) {
    log.info("add user");
    CriteriaSpecification<User> specification = new UserByLoginSpecification(dto.getLogin());
    Optional<User> userOptional = repository.findBySpecification(specification);
    if (userOptional.isEmpty()) {
      dto.setId(0L);
      return userMapper.toDto(repository.create(userMapper.toEntity(dto)).get());
    }
    return userMapper.toDto(userOptional.get());
  }

  @Override
  public UserDto read(long id) {
    log.info("find user {}", id);
    User user = repository.read(id);
    return userMapper.toDto(user);
  }

  @Override
  @Transactional
  public UserDto delete(long id) {
    log.info("delete user {}", id);
    Optional<User> userOptional = repository.remove(id);
    return userMapper.toDto(userOptional.orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id)));
  }

  @Override
  public List<UserDto> allUsers() {
    List<User> users = repository.readAll();
    if (users.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, 0L);
    }
    return userMapper.toDtoList(users);
  }
}
