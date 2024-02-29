package com.carlosarroyoam.book.service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.book.service.constant.AppMessages;
import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.dto.BookResponse;
import com.carlosarroyoam.book.service.entity.Book;
import com.carlosarroyoam.book.service.mapper.BookMapper;

@ApplicationScoped
public class BookService {

	@Inject
	private Logger logger;

	@Inject
	private BookDao bookDao;

	@Inject
	private BookMapper bookMapper;

	public List<BookResponse> findAll() {
		List<Book> books = bookDao.findAll();
		return bookMapper.toDtos(books);
	}

	public BookResponse findByIsbn(String isbn) {
		Book bookByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		return bookMapper.toDto(bookByIsbn);
	}

	@Transactional
	public BookResponse store(Book book) {
		LocalDateTime now = LocalDateTime.now();
		book.setCreatedAt(now);
		book.setUpdatedAt(now);

		bookDao.store(book);
		return bookMapper.toDto(book);
	}

	@Transactional
	public BookResponse update(String isbn, Book book) {
		Book bookByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		bookByIsbn.setTitle(book.getTitle());
		bookByIsbn.setPrice(book.getPrice());
		bookByIsbn.setAvailableOnline(book.isAvailableOnline());
		bookByIsbn.setPublishedAt(book.getPublishedAt());
		bookByIsbn.setUpdatedAt(LocalDateTime.now());

		bookDao.update(book);
		return bookMapper.toDto(book);
	}

	@Transactional
	public void deleteByIsbn(String isbn) {
		Book bookByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		bookDao.deleteByIsbn(bookByIsbn.getIsbn());
	}

}
