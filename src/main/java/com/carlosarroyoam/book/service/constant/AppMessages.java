package com.carlosarroyoam.book.service.constant;

public class AppMessages {
	public static final String STANDARD_ILLEGAL_ACCESS_EXCEPTION_MESSAGE_UTILITY_CLASS = "Illegal access to utility class";

	public static final String BOOK_NOT_FOUND_EXCEPTION = "Book not found";
	public static final String BOOK_NOT_FOUND_WITH_ID = "Book with id: %s was not found";
	public static final String ISBN_ALREADY_EXISTS_EXCEPTION = "Isbn already exists";

	public static final String AUTHOR_NOT_FOUND_EXCEPTION = "Author not found";
	public static final String AUTHOR_NOT_FOUND_WITH_ID = "Author with id: %s was not found";


	private AppMessages() {
		throw new IllegalAccessError(STANDARD_ILLEGAL_ACCESS_EXCEPTION_MESSAGE_UTILITY_CLASS);
	}
}
