package io.kontur.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FeedingDto extends RepresentationModel<FeedingDto> {
  private Long id;
  private Long userId;
  private Long catId;
  private ZonedDateTime feedingTime;
}
