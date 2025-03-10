package com.carlosarroyoam.book.service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NamedQuery(name = Book.FIND_ALL, query = "SELECT b FROM Book b")
@NamedQuery(name = Book.FIND_BY_ISBN, query = "SELECT b FROM Book b WHERE b.isbn = :isbn")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
  public static final String FIND_ALL = "Book.findAll";
  public static final String FIND_BY_ISBN = "Book.findByIsbn";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "isbn", length = 17, nullable = false, unique = true)
  private String isbn;

  @Column(name = "title", length = 128, nullable = false)
  private String title;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "is_available_online", nullable = false)
  private Boolean isAvailableOnline;

  @Builder.Default
  @ManyToMany
  @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
  private List<Author> authors = new ArrayList<>();

  @Column(name = "published_at", nullable = false)
  private LocalDate publishedAt;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
