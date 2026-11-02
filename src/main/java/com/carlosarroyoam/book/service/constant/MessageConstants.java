package com.carlosarroyoam.book.service.constant;

/** Mensajes de error utilizados al registrar y lanzar excepciones de libros y autores. */
public class MessageConstants {
  public static final String BOOK_NOT_FOUND_EXCEPTION = "Book not found";
  public static final String BOOK_NOT_FOUND_WITH_ID = "Book with id: %s was not found";
  public static final String ISBN_ALREADY_EXISTS_EXCEPTION = "Isbn already exists";

  public static final String AUTHOR_NOT_FOUND_EXCEPTION = "Author not found";
  public static final String AUTHOR_NOT_FOUND_WITH_ID = "Author with id: %s was not found";

  /** Constructor privado que impide instanciar la clase. */
  private MessageConstants() {
    throw new IllegalAccessError(CommonConstants.ILLEGAL_ACCESS_ERROR);
  }
}
