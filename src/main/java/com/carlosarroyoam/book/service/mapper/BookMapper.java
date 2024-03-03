package com.carlosarroyoam.book.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.carlosarroyoam.book.service.dto.BookResponse;
import com.carlosarroyoam.book.service.dto.CreateBookRequest;
import com.carlosarroyoam.book.service.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AuthorMapper.class)
public interface BookMapper {

	BookResponse toDto(Book book);

	List<BookResponse> toDtos(List<Book> books);

	Book toEntity(CreateBookRequest createBookRequest);

}
