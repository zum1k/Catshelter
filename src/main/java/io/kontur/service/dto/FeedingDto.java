package io.kontur.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FeedingDto extends RepresentationModel<FeedingDto> {
  private long id;
  @NotNull()
  @Min(value = 0, message = "Value can't be less than 0")
  private long userId;
  private CatDto catDto;
  private ZonedDateTime feedingTime;
}
