package com.carlosarroyoam.book.service.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.carlosarroyoam.book.service.entity.Book;

@ApplicationScoped
@Transactional
public class BookDao {

	private final Logger logger;
	private final EntityManager entityManager;

	public BookDao(Logger logger, EntityManager entityManager) {
		this.logger = logger;
		this.entityManager = entityManager;
	}

	public List<Book> findAll() {
		logger.info("Find all books");
		TypedQuery<Book> query = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
		return query.getResultList();
	}

	public Optional<Book> findByIsbn(String isbn) {
		logger.log(Level.INFO, "Find book with isbn: {0}", isbn);
		Book bookByIsbn = entityManager.find(Book.class, isbn);
		return Optional.ofNullable(bookByIsbn);
	}

	public Book store(Book book) {
		logger.log(Level.INFO, "Create book: {0}", book);
		entityManager.persist(book);
		return book;
	}

	public Book update(String isbn, Book book) {
		logger.log(Level.INFO, "Update book with isbn: {0}", isbn);
		entityManager.merge(book);
		return book;
	}

	public void delete(String isbn) {
		logger.log(Level.INFO, "Delete book with isbn: {0}", isbn);
		Book book = entityManager.getReference(Book.class, isbn);
		entityManager.remove(book);
	}

}
