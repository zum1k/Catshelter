package io.kontur.utils.mapper;

import io.kontur.entity.Feeding;
import io.kontur.service.dto.FeedingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedingMapper {

  @Mapping(target = "id", source = "dto.id")
  Feeding toEntity(FeedingDto dto);

  @Mapping(target = "id", source = "feeding.id")
  FeedingDto toDto(Feeding feeding);

  List<FeedingDto> toDtoList(List<Feeding> feedings);
}
