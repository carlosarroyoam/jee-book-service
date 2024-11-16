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

	public void create(Book book) {
		try {
			logger.log(Level.INFO, "Create book: {0}", book);
			entityManager.getTransaction().begin();
			entityManager.persist(book);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}

			throw e;
		}
	}

	public void update(Book book) {
		try {
			logger.log(Level.INFO, "Update book with isbn: {0}", book.getIsbn());
			entityManager.getTransaction().begin();
			entityManager.merge(book);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}

			throw e;
		}
	}

	public void deleteById(Long bookId) {
		try {
			logger.log(Level.INFO, "Delete book with id: {0}", bookId);
			entityManager.getTransaction().begin();
			Book bookById = entityManager.getReference(Book.class, bookId);
			entityManager.remove(bookById);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}

			throw e;
		}
	}
}
