package com.carlosarroyoam.book.service.service;

import com.carlosarroyoam.book.service.constant.MessageConstants;
import com.carlosarroyoam.book.service.dao.AuthorDao;
import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.AuthorDto.AuthorDtoMapper;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.BookDto.BookDtoMapper;
import com.carlosarroyoam.book.service.entity.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;

/** Lógica de negocio para consultar autores ({@link Author}) y sus libros. */
@ApplicationScoped
public class AuthorService {
  @Inject private Logger logger;
  @Inject private AuthorDao authorDao;

  /**
   * Obtiene todos los autores.
   *
   * @return la lista de {@link AuthorDto} existentes
   */
  public List<AuthorDto> findAll() {
    List<Author> authors = authorDao.findAll();
    return AuthorDtoMapper.INSTANCE.toDtos(authors);
  }

  /**
   * Busca un autor por su id.
   *
   * @param authorId el id del autor
   * @return el {@link AuthorDto} encontrado
   * @throws NotFoundException con 404 si no existe un autor con ese id
   */
  public AuthorDto findById(Long authorId) {
    Author authorById =
        authorDao
            .findById(authorId)
            .orElseThrow(() -> {
              logger.warning(MessageConstants.AUTHOR_NOT_FOUND_EXCEPTION);
              return new NotFoundException(
                  String.format(MessageConstants.AUTHOR_NOT_FOUND_WITH_ID, authorId));
            });

    return AuthorDtoMapper.INSTANCE.toDto(authorById);
  }

  /**
   * Obtiene los libros de un autor.
   *
   * @param authorId el id del autor
   * @return la lista de {@link BookDto} escritos por el autor
   * @throws NotFoundException con 404 si no existe un autor con ese id
   */
  public List<BookDto> findBooksByAuthorId(Long authorId) {
    Author authorById =
        authorDao
            .findById(authorId)
            .orElseThrow(() -> {
              logger.warning(MessageConstants.AUTHOR_NOT_FOUND_EXCEPTION);
              return new NotFoundException(
                  String.format(MessageConstants.AUTHOR_NOT_FOUND_WITH_ID, authorId));
            });

    return BookDtoMapper.INSTANCE.toDtos(authorById.getBooks());
  }
}
