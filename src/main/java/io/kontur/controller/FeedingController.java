package io.kontur.controller;

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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/feedings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedingController {
  private final FeedingService service;
  private final FeedingLinkModifier feedingLinkModifier;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<FeedingDto>> findAll() {
    log.info("find all feedings");
    List<FeedingDto> dtos = service.readAll();
    return ResponseEntity.ok().body(feedingLinkModifier.allWithPagination(dtos));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<FeedingDto> findFeedingByUser(
      @PathVariable("id")
      @Min(value = 1, message = "id must be positive") final long id) {
    log.info("find feeding by id");
    FeedingDto dto = service.read(id);
    feedingLinkModifier.withTagLocation(dto);
    return ResponseEntity.ok().body(dto);
  }
}
