package com.carlosarroyoam.book.service.service;

import com.carlosarroyoam.book.service.constant.AppMessages;
import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.CreateBookRequestDto;
import com.carlosarroyoam.book.service.dto.UpdateBookRequestDto;
import com.carlosarroyoam.book.service.entity.Book;
import com.carlosarroyoam.book.service.mapper.AuthorMapper;
import com.carlosarroyoam.book.service.mapper.BookMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class BookService {
  @Inject
  private Logger logger;

  @Inject
  private BookDao bookDao;

  @Inject
  private BookMapper bookMapper;

  @Inject
  private AuthorMapper authorMapper;

  public List<BookDto> findAll() {
    List<Book> books = bookDao.findAll();
    return bookMapper.toDtos(books);
  }

  public BookDto findById(Long bookId) {
    Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
      logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
    });

    return bookMapper.toDto(bookById);
  }

  public BookDto create(CreateBookRequestDto requestDto) {
    if (bookDao.findByIsbn(requestDto.getIsbn()).isPresent()) {
      logger.warning(AppMessages.ISBN_ALREADY_EXISTS_EXCEPTION);
      throw new BadRequestException(AppMessages.ISBN_ALREADY_EXISTS_EXCEPTION);
    }

    LocalDateTime now = LocalDateTime.now();
    Book book = bookMapper.toEntity(requestDto);
    book.setCreatedAt(now);
    book.setUpdatedAt(now);

    bookDao.create(book);
    return bookMapper.toDto(book);
  }

  public void update(Long bookId, UpdateBookRequestDto requestDto) {
    Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
      logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
    });

    bookById.setTitle(requestDto.getTitle());
    bookById.setPrice(requestDto.getPrice());
    bookById.setIsAvailableOnline(requestDto.getIsAvailableOnline());
    bookById.setPublishedAt(requestDto.getPublishedAt());
    bookById.setUpdatedAt(LocalDateTime.now());

    bookDao.update(bookById);
  }

  public void deleteById(Long bookId) {
    Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
      logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
    });

    bookDao.deleteById(bookById.getId());
  }

  public List<AuthorDto> findAuthorsByBookId(Long bookId) {
    Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
      logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
      throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
    });

    return authorMapper.toDtos(bookById.getAuthors());
  }
}
