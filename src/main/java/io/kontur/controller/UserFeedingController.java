package io.kontur.controller;

import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;
import io.kontur.service.feeding.FeedingService;
import io.kontur.utils.resource.FeedingLinkModifier;
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
@RequestMapping("/users/{id}/feedings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFeedingController {
  private final FeedingService service;
  private final FeedingLinkModifier modifier;

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<FeedingDto> addFeeding(
      @PathVariable("id")
      @Min(value = 1, message = "id must be positive") final long id,
      @Valid @RequestBody CatDto dto) {
    log.info("add feeding");
    FeedingDto feedingDto = new FeedingDto();
    feedingDto.setUserId(id);
    feedingDto.setCatDto(dto);
    FeedingDto resultDto = service.create(feedingDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(resultDto.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<FeedingDto>> findAllByUserId(
      @PathVariable("id")
      @Min(value = 1, message = "id must be positive") final long id) {
    log.info("find all feedings by user {}", id);
    List<FeedingDto> feedingDtoList = service.findUserFeeding(id);
    return ResponseEntity.ok().body(modifier.allWithPagination(feedingDtoList));
  }

}
