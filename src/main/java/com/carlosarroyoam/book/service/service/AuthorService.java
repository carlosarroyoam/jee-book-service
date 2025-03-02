package com.carlosarroyoam.book.service.service;

import com.carlosarroyoam.book.service.constant.AppMessages;
import com.carlosarroyoam.book.service.dao.AuthorDao;
import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.AuthorDto.AuthorDtoMapper;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.BookDto.BookDtoMapper;
import com.carlosarroyoam.book.service.entity.Author;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class AuthorService {
  @Inject
  private Logger logger;

  @Inject
  private AuthorDao authorDao;

  public List<AuthorDto> findAll() {
    List<Author> authors = authorDao.findAll();
    return AuthorDtoMapper.INSTANCE.toDtos(authors);
  }

  public AuthorDto findById(Long authorId) {
    Author authorById = authorDao.findById(authorId).orElseThrow(() -> {
      logger.warning(AppMessages.AUTHOR_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.AUTHOR_NOT_FOUND_WITH_ID, authorId));
    });

    return AuthorDtoMapper.INSTANCE.toDto(authorById);
  }

  public List<BookDto> findBooksByAuthorId(Long authorId) {
    Author authorById = authorDao.findById(authorId).orElseThrow(() -> {
      logger.warning(AppMessages.AUTHOR_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.AUTHOR_NOT_FOUND_WITH_ID, authorId));
    });

    return BookDtoMapper.INSTANCE.toDtos(authorById.getBooks());
  }
}
