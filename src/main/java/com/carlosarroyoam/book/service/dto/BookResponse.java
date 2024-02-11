package com.carlosarroyoam.book.service.dto;

import lombok.Data;

@Data
public class BookResponse {

	private String isbn;
	private String title;
	private AuthorResponse author;

}
