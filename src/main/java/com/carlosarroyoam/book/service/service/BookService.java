package com.carlosarroyoam.book.service.service;

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
	private BookDao bookRepository;

	@Inject
	private BookMapper bookMapper;

	public List<BookResponse> findAll() {
		List<Book> books = bookRepository.findAll();
		return bookMapper.toDtos(books);
	}

	public BookResponse findByIsbn(String isbn) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		return bookMapper.toDto(bookByIsbn);
	}

	@Transactional
	public BookResponse store(Book book) {
		bookRepository.store(book);
		return bookMapper.toDto(book);
	}

	@Transactional
	public BookResponse update(String isbn, Book book) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		bookByIsbn.setTitle(book.getTitle());
		bookByIsbn.setAuthor(book.getAuthor());

		bookRepository.update(book);
		return bookMapper.toDto(book);
	}

	@Transactional
	public void deleteByIsbn(String isbn) {
		Book bookByIsbn = bookRepository.findByIsbn(isbn).orElseThrow(() -> {
			logger.warning(AppMessages.BOOK_NOT_FOUND_EXCEPTION);
			throw new NotFoundException(String.format(AppMessages.BOOK_NOT_FOUND_WITH_ISBN, isbn));
		});

		bookRepository.deleteByIsbn(bookByIsbn.getIsbn());
	}

}
