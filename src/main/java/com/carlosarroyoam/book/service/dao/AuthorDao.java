package com.carlosarroyoam.book.service.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.carlosarroyoam.book.service.entity.Author;

@ApplicationScoped
public class AuthorDao {

	@Inject
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	public List<Author> findAll() {
		logger.info("Find all authors");
		TypedQuery<Author> query = entityManager.createNamedQuery(Author.FIND_ALL, Author.class);
		return query.getResultList();
	}

	public Optional<Author> findById(Long authorId) {
		logger.log(Level.INFO, "Find author with id: {0}", authorId);
		Author authorById = entityManager.find(Author.class, authorId);
		return Optional.ofNullable(authorById);
	}

}
