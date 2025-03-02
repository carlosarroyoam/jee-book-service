package com.carlosarroyoam.book.service.mapper;

import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.CreateBookRequestDto;
import com.carlosarroyoam.book.service.entity.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AuthorMapper.class)
public interface BookMapper {
  BookDto toDto(Book book);

  List<BookDto> toDtos(List<Book> books);

  Book toEntity(CreateBookRequestDto requestDto);
}
