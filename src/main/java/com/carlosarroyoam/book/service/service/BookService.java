package com.carlosarroyoam.book.service.service;

import com.carlosarroyoam.book.service.constant.MessageConstants;
import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.AuthorDto.AuthorDtoMapper;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.BookDto.BookDtoMapper;
import com.carlosarroyoam.book.service.dto.CreateBookRequestDto;
import com.carlosarroyoam.book.service.dto.UpdateBookRequestDto;
import com.carlosarroyoam.book.service.entity.Book;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

/** Lógica de negocio para consultar y administrar libros ({@link Book}) y sus autores. */
@ApplicationScoped
public class BookService {
  @Inject private Logger logger;
  @Inject private BookDao bookDao;
  @Inject private Clock clock;

  /**
   * Obtiene todos los libros.
   *
   * @return la lista de {@link BookDto} existentes
   */
  public List<BookDto> findAll() {
    List<Book> books = bookDao.findAll();
    return BookDtoMapper.INSTANCE.toDtos(books);
  }

  /**
   * Busca un libro por su id.
   *
   * @param bookId el id del libro
   * @return el {@link BookDto} encontrado
   * @throws NotFoundException con 404 si no existe un libro con ese id
   */
  public BookDto findById(Long bookId) {
    Book bookById =
        bookDao
            .findById(bookId)
            .orElseThrow(() -> {
              logger.warning(MessageConstants.BOOK_NOT_FOUND_EXCEPTION);
              return new NotFoundException(
                  String.format(MessageConstants.BOOK_NOT_FOUND_WITH_ID, bookId));
            });

    return BookDtoMapper.INSTANCE.toDto(bookById);
  }

  /**
   * Crea un libro y registra sus fechas de creación y actualización.
   *
   * @param requestDto los datos del libro a crear
   * @return el {@link BookDto} creado
   * @throws BadRequestException con 400 si ya existe un libro con ese ISBN
   */
  public BookDto create(CreateBookRequestDto requestDto) {
    if (bookDao.findByIsbn(requestDto.getIsbn()).isPresent()) {
      logger.warning(MessageConstants.ISBN_ALREADY_EXISTS_EXCEPTION);
      throw new BadRequestException(MessageConstants.ISBN_ALREADY_EXISTS_EXCEPTION);
    }

    LocalDateTime now = LocalDateTime.now(clock);
    Book book = BookDtoMapper.INSTANCE.toEntity(requestDto);
    book.setCreatedAt(now);
    book.setUpdatedAt(now);
    bookDao.create(book);

    return BookDtoMapper.INSTANCE.toDto(book);
  }

  /**
   * Actualiza el título, precio, disponibilidad en línea y fecha de publicación de un libro.
   *
   * @param bookId el id del libro a actualizar
   * @param requestDto los nuevos datos del libro
   * @throws NotFoundException con 404 si no existe un libro con ese id
   */
  public void update(Long bookId, UpdateBookRequestDto requestDto) {
    Book bookById =
        bookDao
            .findById(bookId)
            .orElseThrow(() -> {
              logger.warning(MessageConstants.BOOK_NOT_FOUND_EXCEPTION);
              return new NotFoundException(
                  String.format(MessageConstants.BOOK_NOT_FOUND_WITH_ID, bookId));
            });

    LocalDateTime now = LocalDateTime.now(clock);
    bookById.setTitle(requestDto.getTitle());
    bookById.setPrice(requestDto.getPrice());
    bookById.setIsAvailableOnline(requestDto.getIsAvailableOnline());
    bookById.setPublishedAt(requestDto.getPublishedAt());
    bookById.setUpdatedAt(now);
    bookDao.update(bookById);
  }

  /**
   * Elimina un libro por su id.
   *
   * @param bookId el id del libro a eliminar
   * @throws NotFoundException con 404 si no existe un libro con ese id
   */
  public void deleteById(Long bookId) {
    if (!bookDao.deleteById(bookId)) {
      logger.warning(MessageConstants.BOOK_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(MessageConstants.BOOK_NOT_FOUND_WITH_ID, bookId));
    }
  }

  /**
   * Obtiene los autores de un libro.
   *
   * @param bookId el id del libro
   * @return la lista de {@link AuthorDto} del libro
   * @throws NotFoundException con 404 si no existe un libro con ese id
   */
  public List<AuthorDto> findAuthorsByBookId(Long bookId) {
    Book bookById =
        bookDao
            .findById(bookId)
            .orElseThrow(() -> {
              logger.warning(MessageConstants.BOOK_NOT_FOUND_EXCEPTION);
              return new NotFoundException(
                  String.format(MessageConstants.BOOK_NOT_FOUND_WITH_ID, bookId));
            });

    return AuthorDtoMapper.INSTANCE.toDtos(bookById.getAuthors());
  }
}
