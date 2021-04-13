package io.kontur.utils.mapper;

import io.kontur.entity.Cat;
import io.kontur.service.dto.CatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {
  @Mapping(target = "feedings", ignore = true)
  Cat toEntity(CatDto dto);

  CatDto toDto(Cat cat);

  List<CatDto> toDtoList(List<Cat> cats);
}
