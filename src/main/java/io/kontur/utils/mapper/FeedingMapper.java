package io.kontur.utils.mapper;

import io.kontur.entity.Feeding;
import io.kontur.service.dto.FeedingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {CatMapper.class, UserMapper.class}
)
public interface FeedingMapper {
  @Mappings({
      @Mapping(target = "user", ignore = true),
      @Mapping(target = "cat", ignore = true),
      @Mapping(target = "id", source = "dto.id")
  })
  Feeding toEntity(FeedingDto dto);

  @Mappings({
      @Mapping(target = "id", source = "feeding.id"),
      @Mapping(target = "userId", source = "feeding.user.id"),
      @Mapping(target = "catDto", source = "feeding.cat")
  })
  FeedingDto toDto(Feeding feeding);

  List<FeedingDto> toDtoList(List<Feeding> feedings);
}
