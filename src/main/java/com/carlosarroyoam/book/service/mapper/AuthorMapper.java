package com.carlosarroyoam.book.service.mapper;

import com.carlosarroyoam.book.service.dto.AuthorResponse;
import com.carlosarroyoam.book.service.entity.Author;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {
  AuthorResponse toDto(Author author);

  List<AuthorResponse> toDtos(List<Author> authors);
}
