package com.carlosarroyoam.library.model;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonbPropertyOrder({ "id", "name" })
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
}
