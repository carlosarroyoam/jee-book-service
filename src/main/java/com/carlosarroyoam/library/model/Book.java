package com.carlosarroyoam.library.model;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonbPropertyOrder({ "isbn", "title", "author" })
public class Book {
	@Id
	@Column(name = "isbn", unique = true)
	private String isbn;

	@Column(name = "title", nullable = false)
	private String title;

	private Author author;
}
