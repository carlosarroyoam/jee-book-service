package com.carlosarroyoam.book.service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "authors")
@NamedQuery(name = Author.FIND_ALL, query = "SELECT a FROM Author a")
@Data
public class Author {

	public static final String FIND_ALL = "Author.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 128, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "authors")
	private List<Book> books = new ArrayList<>();

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}
