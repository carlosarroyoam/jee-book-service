package com.carlosarroyoam.book.service.dao;

import com.carlosarroyoam.book.service.entity.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Acceso a datos para la entidad {@link Author}. */
@ApplicationScoped
public class AuthorDao {
  @Inject private Logger logger;
  @Inject private EntityManager entityManager;

  /**
   * Obtiene todos los autores.
   *
   * @return la lista de {@link Author} existentes
   */
  public List<Author> findAll() {
    logger.info("Find all authors");
    TypedQuery<Author> query = entityManager.createNamedQuery(Author.FIND_ALL, Author.class);
    return query.getResultList();
  }

  /**
   * Busca un autor por su id.
   *
   * @param authorId el id del autor
   * @return el autor encontrado, o {@link Optional#empty()} si no existe ninguno con ese id
   */
  public Optional<Author> findById(Long authorId) {
    logger.log(Level.INFO, "Find author with id: {0}", authorId);
    Author authorById = entityManager.find(Author.class, authorId);
    return Optional.ofNullable(authorById);
  }
}
