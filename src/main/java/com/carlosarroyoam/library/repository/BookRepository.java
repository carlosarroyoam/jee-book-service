package com.carlosarroyoam.library.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.carlosarroyoam.library.model.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unchecked")
public class BookRepository {
	@Inject
	private EntityManager entityManager;

	/**
	 * Retrieves the list of all books.
	 *
	 * @return a collection of books
	 */
	public List<Book> findAll() {
		Query query = entityManager.createQuery("SELECT b FROM Book b");

		List<Book> books = query.getResultList();

		return books;
	}

	/**
	 * Retrieves a book by its ISBN.
	 *
	 * @param isbn the ISBN
	 * @return the book
	 */
	public Optional<Book> findByISBN(String isbn) {
		Book bookByIsbn = entityManager.find(Book.class, isbn);

		return Optional.ofNullable(bookByIsbn);
	}
}
