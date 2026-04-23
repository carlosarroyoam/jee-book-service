package com.carlosarroyoam.book.service.dto;

import com.carlosarroyoam.book.service.entity.Author;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Getter
@Setter
@Builder
public class AuthorDto {
  private Long id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Mapper(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      unmappedTargetPolicy = ReportingPolicy.IGNORE)
  public interface AuthorDtoMapper {
    AuthorDtoMapper INSTANCE = Mappers.getMapper(AuthorDtoMapper.class);

    AuthorDto toDto(Author author);

    List<AuthorDto> toDtos(List<Author> authors);
  }
}
