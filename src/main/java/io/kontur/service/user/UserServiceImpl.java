package io.kontur.service.user;

import io.kontur.entity.Cat;
import io.kontur.entity.Feeding;
import io.kontur.entity.User;
import io.kontur.repository.Repository;
import io.kontur.repository.specification.CatByNameSpecification;
import io.kontur.repository.specification.CriteriaSpecification;
import io.kontur.repository.specification.UserByLoginSpecification;
import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;
import io.kontur.service.dto.UserDto;
import io.kontur.utils.mapper.CatMapper;
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
public class UserServiceImpl implements UserService{
  private static final String ENTITY_NAME = "User";
  private final Repository<User> repository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public Feeding create(UserDto dto) {
    log.info("add cat");
    CriteriaSpecification<User> specification = new UserByLoginSpecification(dto.getLogin());
    Optional<User> userOptional = repository.findBySpecification(specification);
    if (userOptional.isEmpty()) {
      dto.setId(0L);
      return userMapper.toDto(repository.create(userMapper.toEntity(dto)).get());
    }
    return userMapper.toDto(userOptional.get());
  }

  @Override
  public CatDto read(long id) {
    return null;
  }

  @Override
  public CatDto delete(long id) {
    return null;
  }

  @Override
  public List<FeedingDto> findUserFeeding(long userId) {
    return null;
  }

  @Override
  public List<FeedingDto> findCatFeeding(long catId) {
    return null;
  }
}
