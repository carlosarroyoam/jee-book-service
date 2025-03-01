package com.carlosarroyoam.book.service.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.carlosarroyoam.book.service.dao.BookDao;
import com.carlosarroyoam.book.service.dto.BookResponse;
import com.carlosarroyoam.book.service.mapper.BookMapper;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
  @Mock
  private Logger logger;

  @Spy
  private BookMapper bookMapper;

  @Mock
  private BookDao bookDao;

  @InjectMocks
  private BookService bookService;

  @Test
  void shouldReturnListOfUsers() {
    when(bookDao.findAll()).thenReturn(Collections.emptyList());

    List<BookResponse> books = bookService.findAll();

    assertThat(books, is(notNullValue()));
    assertThat(books.size(), is(0));
  }
}
