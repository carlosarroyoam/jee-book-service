package com.carlosarroyoam.library.model;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "isbn", unique = true)
	@Pattern(regexp = "[0-9]{10}")
	private String isbn;

	@Column(name = "title", nullable = false)
	@NotBlank
	private String title;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	private Author author;
}
