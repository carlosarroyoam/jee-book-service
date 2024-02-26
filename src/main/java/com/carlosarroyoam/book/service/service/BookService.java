package com.carlosarroyoam.book.service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.book.service.constants.AppMessages;
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
		Book findByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		return bookMapper.toDto(findByIsbn);
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
		Book findByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		findByIsbn.setTitle(book.getTitle());
		findByIsbn.setPrice(book.getPrice());
		findByIsbn.setAvailableOnline(book.isAvailableOnline());
		findByIsbn.setPublishedAt(book.getPublishedAt());
		findByIsbn.setUpdatedAt(LocalDateTime.now());

		bookDao.update(book);
		return bookMapper.toDto(book);
	}

	@Transactional
	public void deleteByIsbn(String isbn) {
		Book findByIsbn = bookDao.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		bookDao.deleteByIsbn(findByIsbn.getIsbn());
	}

}
