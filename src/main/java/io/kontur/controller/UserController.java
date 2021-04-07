package io.kontur.controller;

import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.UserDto;
import io.kontur.service.feeding.FeedingService;
import io.kontur.service.user.UserServiceImpl;
import io.kontur.utils.resource.FeedingLinkModifier;
import io.kontur.utils.resource.UserLinkModifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserLinkModifier linkModifier;
  private final FeedingLinkModifier feedingLinkModifier;
  private final UserServiceImpl userService;
  private final FeedingService feedingService;

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto dto) {
    log.info("add user");
    UserDto userDto = userService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(userDto.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDto> findUser(@PathVariable("id")
                                          @Min(value = 1, message = "id must be positive") final int id) {
    log.info("find user {}", id);
    UserDto userDto = userService.read(id);
    linkModifier.withTagLocation(userDto);
    return ResponseEntity.ok().body(userDto);
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<UserDto>> findAll() {
    log.info("find all users");
    List<UserDto> userDtos = userService.allUsers();
    return ResponseEntity.ok().body(linkModifier.allWithPagination(userDtos));
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CatDto> deleteCatById(@PathVariable("id")
                                              @Min(value = 1, message = "id must be positive") final int id) {
    log.info("get user {}", id);
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
