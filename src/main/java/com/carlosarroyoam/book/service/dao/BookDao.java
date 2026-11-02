package com.carlosarroyoam.book.service.dao;

import com.carlosarroyoam.book.service.entity.Book;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/** Acceso a datos para la entidad {@link Book}. */
@ApplicationScoped
public class BookDao {
  @Inject private Logger logger;
  @Inject private EntityManager entityManager;

  /**
   * Obtiene todos los libros.
   *
   * @return la lista de {@link Book} existentes
   */
  public List<Book> findAll() {
    logger.info("Find all books");
    TypedQuery<Book> query = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
    return query.getResultList();
  }

  /**
   * Busca un libro por su id.
   *
   * @param bookId el id del libro
   * @return el libro encontrado, o {@link Optional#empty()} si no existe ninguno con ese id
   */
  public Optional<Book> findById(Long bookId) {
    logger.log(Level.INFO, "Find book with id: {0}", bookId);
    Book bookById = entityManager.find(Book.class, bookId);
    return Optional.ofNullable(bookById);
  }

  /**
   * Busca un libro por su ISBN.
   *
   * @param isbn el ISBN del libro
   * @return el libro encontrado, o {@link Optional#empty()} si no existe ninguno con ese ISBN
   */
  public Optional<Book> findByIsbn(String isbn) {
    logger.log(Level.INFO, "Find book with isbn: {0}", isbn);
    TypedQuery<Book> query = entityManager.createNamedQuery(Book.FIND_BY_ISBN, Book.class);
    List<Book> results = query.setParameter("isbn", isbn).getResultList();

    if (results.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(results.get(0));
  }

  /**
   * Persiste un libro dentro de una transacción. Si la operación falla, revierte la transacción y
   * relanza la excepción.
   *
   * @param book el libro a persistir
   */
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

  /**
   * Actualiza un libro dentro de una transacción. Si la operación falla, revierte la transacción y
   * relanza la excepción.
   *
   * @param book el libro con los datos actualizados
   */
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

  /**
   * Elimina un libro por su id dentro de una transacción. Si la operación falla, revierte la
   * transacción y relanza la excepción.
   *
   * @param bookId el id del libro a eliminar
   */
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
