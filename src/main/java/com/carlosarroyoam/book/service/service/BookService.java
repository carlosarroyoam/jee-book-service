package com.carlosarroyoam.book.service.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.book.service.constants.AppMessages;
import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.entity.Book;

@ApplicationScoped
public class BookService {

	private final Logger logger;
	private final BookDao bookRepository;

	public BookService(Logger logger, BookDao bookRepository) {
		this.logger = logger;
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findByIsbn(String isbn) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format("Book with isbn: %s was not found", isbn));
		});

		return bookByIsbn;
	}

	public Book store(Book book) {
		return bookRepository.store(book);
	}

	public Book update(String isbn, Book book) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format("Book with isbn: %s was not found", isbn));
		});

		return bookRepository.update(bookByIsbn.getIsbn(), book);
	}

	public void deleteByIsbn(String isbn) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format("Book with isbn: %s was not found", isbn));
		});

		bookRepository.delete(bookByIsbn.getIsbn());
	}

}
