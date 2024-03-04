package com.carlosarroyoam.book.service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.book.service.constant.AppMessages;
import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.dto.AuthorResponse;
import com.carlosarroyoam.book.service.dto.BookResponse;
import com.carlosarroyoam.book.service.dto.CreateBookRequest;
import com.carlosarroyoam.book.service.dto.UpdateBookRequest;
import com.carlosarroyoam.book.service.entity.Book;
import com.carlosarroyoam.book.service.mapper.AuthorMapper;
import com.carlosarroyoam.book.service.mapper.BookMapper;

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

	public List<BookResponse> findAll() {
		List<Book> books = bookDao.findAll();
		return bookMapper.toDtos(books);
	}

	public BookResponse findById(Long bookId) {
		Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
		});

		return bookMapper.toDto(bookById);
	}

	public BookResponse create(CreateBookRequest createBookRequest) {
		boolean existsByIsbn = bookDao.findByIsbn(createBookRequest.getIsbn()).isPresent();
		if (existsByIsbn) {
			logger.warning(AppMessages.ISBN_ALREADY_EXISTS_EXCEPTION);
			throw new BadRequestException(AppMessages.ISBN_ALREADY_EXISTS_EXCEPTION);
		}

		LocalDateTime now = LocalDateTime.now();
		Book book = bookMapper.toEntity(createBookRequest);
		book.setCreatedAt(now);
		book.setUpdatedAt(now);

		bookDao.create(book);
		return bookMapper.toDto(book);
	}

	public void update(Long bookId, UpdateBookRequest updateBookRequest) {
		Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
		});

		bookById.setTitle(updateBookRequest.getTitle());
		bookById.setPrice(updateBookRequest.getPrice());
		bookById.setIsAvailableOnline(updateBookRequest.getIsAvailableOnline());
		bookById.setPublishedAt(updateBookRequest.getPublishedAt());
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

	public List<AuthorResponse> findAuthorsByBookId(Long bookId) {
		Book bookById = bookDao.findById(bookId).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ID, bookId));
		});

		return authorMapper.toDtos(bookById.getAuthors());
	}

}
