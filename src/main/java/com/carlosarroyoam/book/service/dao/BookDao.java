package com.carlosarroyoam.book.service.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.carlosarroyoam.book.service.entity.Book;

@ApplicationScoped
public class BookDao {

	@Inject
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	public List<Book> findAll() {
		logger.info("Find all books");
		TypedQuery<Book> query = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
		return query.getResultList();
	}

	public Optional<Book> findById(Long bookId) {
		logger.log(Level.INFO, "Find book with id: {0}", bookId);
		Book bookById = entityManager.find(Book.class, bookId);
		return Optional.ofNullable(bookById);
	}

	public Optional<Book> findByIsbn(String isbn) {
		logger.log(Level.INFO, "Find book with isbn: {0}", isbn);
		TypedQuery<Book> query = entityManager.createNamedQuery(Book.FIND_BY_ISBN, Book.class);
		List<Book> results = query.setParameter("isbn", isbn).getResultList();

		if (results.isEmpty())
			return Optional.empty();

		return Optional.of(results.get(0));
	}

	public void store(Book book) {
		logger.log(Level.INFO, "Create book: {0}", book);
		entityManager.persist(book);
	}

	public void update(Book book) {
		logger.log(Level.INFO, "Update book with isbn: {0}", book.getIsbn());
		entityManager.merge(book);
	}

	public void deleteById(Long bookId) {
		logger.log(Level.INFO, "Delete book with id: {0}", bookId);
		Book bookById = entityManager.getReference(Book.class, bookId);
		entityManager.remove(bookById);
	}

}
