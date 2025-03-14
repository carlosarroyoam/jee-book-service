package com.carlosarroyoam.book.service.resource;

import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.CreateBookRequestDto;
import com.carlosarroyoam.book.service.dto.UpdateBookRequestDto;
import com.carlosarroyoam.book.service.service.BookService;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/books")
@ApplicationScoped
public class BookResource {
  @Inject
  private BookService bookService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    List<BookDto> books = bookService.findAll();
    return Response.ok(books).build();
  }

  @GET
  @Path("/{bookId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("bookId") Long bookId) {
    BookDto bookById = bookService.findById(bookId);
    return Response.ok(bookById).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response store(@Valid CreateBookRequestDto requestDto) {
    BookDto createdBook = bookService.create(requestDto);
    URI locationUri = UriBuilder.fromResource(BookResource.class)
        .path("/{bookId}")
        .resolveTemplate("bookId", createdBook.getId())
        .build();
    return Response.created(locationUri).build();
  }

  @PUT
  @Path("/{bookId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("bookId") Long bookId, @Valid UpdateBookRequestDto requestDto) {
    bookService.update(bookId, requestDto);
    return Response.noContent().build();
  }

  @DELETE
  @Path("/{bookId}")
  public Response deleteById(@PathParam("bookId") Long bookId) {
    bookService.deleteById(bookId);
    return Response.noContent().build();
  }

  @GET
  @Path("/{bookId}/authors")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findBookAuthors(@PathParam("bookId") Long bookId) {
    List<AuthorDto> authors = bookService.findAuthorsByBookId(bookId);
    return Response.ok(authors).build();
  }
}
