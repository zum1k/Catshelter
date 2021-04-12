package io.kontur.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CatDto extends RepresentationModel<CatDto> {
  private long id;
  private String name;
  private ZonedDateTime createDate;
  private ZonedDateTime lastUpdateDate;
}
