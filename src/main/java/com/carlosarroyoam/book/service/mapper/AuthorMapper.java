package com.carlosarroyoam.book.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.carlosarroyoam.book.service.dto.AuthorResponse;
import com.carlosarroyoam.book.service.entity.Author;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

	AuthorResponse toDto(Author author);

	List<AuthorResponse> toDtos(List<Author> authors);

}
