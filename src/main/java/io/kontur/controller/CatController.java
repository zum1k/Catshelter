package io.kontur.controller;

import io.kontur.service.cat.CatService;
import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;
import io.kontur.service.feeding.FeedingService;
import io.kontur.utils.resource.CatLinkModifier;
import io.kontur.utils.resource.FeedingLinkModifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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
@RequestMapping("/cats")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatController {
  private final CatService catService;
  private final FeedingService feedingService;
  private final CatLinkModifier linkModifier;
  private final FeedingLinkModifier feedingLinkModifier;

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CatDto> addCat(@Valid @RequestBody CatDto dto) {
    log.info("add cat");
    CatDto catDto = catService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(catDto.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CatDto> findCatById(@PathVariable("id")
                                            @Min(value = 1, message = "id must be positive") final long id) {
    log.info("get cat {}", id);
    CatDto dto = catService.read(id);
    linkModifier.withTagLocation(dto);
    return ResponseEntity.ok().body(dto);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<CatDto>> allCats() {
    log.info("find all cats");
    List<CatDto> dtos = catService.allCats();
    return ResponseEntity.ok().body(linkModifier.allWithPagination(dtos));
  }
  @SubscribeMapping
  @RequestMapping(
      value = "/actions/hungry_cats",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<CatDto>> hungryCats(@RequestBody List<CatDto> hungryCats) {
    log.info("get hungry cats");
    if (hungryCats.isEmpty()) {
      List<CatDto> dtos = catService.findHungryCats();
      return ResponseEntity.ok().body(linkModifier.allWithPagination(dtos));
    }
    return null;
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CatDto> deleteCatById(
      @PathVariable("id")
      @Min(value = 1, message = "id must be positive") final long id) {
    log.info("get cat {}", id);
    catService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(
      value = "/{id}/feedings",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<FeedingDto>> findAllFeedingByCatId(
      @PathVariable("id")
      @Min(value = 1, message = "id must be positive") final int id) {
    log.info("find all feedings by cat {}", id);
    List<FeedingDto> dtos = feedingService.findCatFeeding(id);
    return ResponseEntity.ok().body(feedingLinkModifier.allWithPagination(dtos));
  }
}
