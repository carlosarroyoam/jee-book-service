package com.carlosarroyoam.book.service.constant;

public class MessageConstants {
  public static final String BOOK_NOT_FOUND_EXCEPTION = "Book not found";
  public static final String BOOK_NOT_FOUND_WITH_ID = "Book with id: %s was not found";
  public static final String ISBN_ALREADY_EXISTS_EXCEPTION = "Isbn already exists";

  public static final String AUTHOR_NOT_FOUND_EXCEPTION = "Author not found";
  public static final String AUTHOR_NOT_FOUND_WITH_ID = "Author with id: %s was not found";

  private MessageConstants() {
    throw new IllegalAccessError(CommonConstants.ILLEGAL_ACCESS_ERROR);
  }
}
