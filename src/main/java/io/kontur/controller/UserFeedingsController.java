package io.kontur.controller;

import io.kontur.service.dto.FeedingDto;
import io.kontur.service.feeding.FeedingService;
import io.kontur.utils.resource.FeedingLinkModifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/{id}/feedings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFeedingsController {
  private final FeedingService service;
  private final FeedingLinkModifier modifier;

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<FeedingDto> addFeeding(@Valid @RequestBody FeedingDto dto) {
    log.info("add feeding");
    FeedingDto feedingDto = service.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(feedingDto.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
