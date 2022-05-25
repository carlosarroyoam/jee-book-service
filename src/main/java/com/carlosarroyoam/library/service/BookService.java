package com.carlosarroyoam.library.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.carlosarroyoam.library.model.Author;
import com.carlosarroyoam.library.model.Book;
import com.carlosarroyoam.library.repository.BookRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApplicationScoped
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookService {
	@Inject
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findByIsbn(String isbn) {
		Optional<Book> bookByIsbn = bookRepository.findByISBN(isbn);

		if (!bookByIsbn.isPresent()) {
			throw new NotFoundException(String.format("Book with isbn: %s was not found", isbn));
		}

		return bookByIsbn.get();
	}

	public Book store(Book book) {
		Optional<Book> bookByIsbn = Optional.ofNullable(
				new Book("2", "Mastering Java EE 8 Application Development", new Author(1L, "Kapila Bogahapitiya")));

		return bookByIsbn.get();
	}

	public Book update(Book book, String isbn) {
		Optional<Book> bookByIsbn = Optional.ofNullable(
				new Book(isbn, "Mastering Java EE 8 Application Development", new Author(1L, "Kapila Bogahapitiya")));

		return bookByIsbn.get();
	}

	public void deleteByIsbn(String isbn) {
		Optional<Book> bookByIsbn = Optional.ofNullable(
				new Book(isbn, "Mastering Java EE 8 Application Development", new Author(1L, "Kapila Bogahapitiya")));

		if (!bookByIsbn.isPresent()) {
			throw new NotFoundException(String.format("Book with isbn: %s was not found", isbn));
		}
	}
}
