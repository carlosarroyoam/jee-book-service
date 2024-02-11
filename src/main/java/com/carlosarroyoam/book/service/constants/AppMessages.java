package com.carlosarroyoam.book.service.constants;

public class AppMessages {

	public static final String STANDARD_ILLEGAL_ACCESS_EXCEPTION_MESSAGE_UTILITY_CLASS = "Illegal access to utility class";

	public static final String BOOK_NOT_FOUND_EXCEPTION = "Book not found";

	private AppMessages() {
		throw new IllegalAccessError(STANDARD_ILLEGAL_ACCESS_EXCEPTION_MESSAGE_UTILITY_CLASS);
	}

}
