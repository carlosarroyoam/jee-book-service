package com.carlosarroyoam.book.service.resource;

import com.carlosarroyoam.book.service.dto.AuthorDto;
import com.carlosarroyoam.book.service.dto.BookDto;
import com.carlosarroyoam.book.service.dto.CreateBookRequestDto;
import com.carlosarroyoam.book.service.dto.UpdateBookRequestDto;
import com.carlosarroyoam.book.service.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/** Expone los endpoints REST para administrar libros bajo {@code /books}. */
@Path("/books")
@ApplicationScoped
public class BookResource {
  @Inject private BookService bookService;

  /**
   * Obtiene todos los libros mediante {@code GET /books}.
   *
   * @return la lista de {@link BookDto} y el estado 200 OK
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    List<BookDto> books = bookService.findAll();
    return Response.ok(books).build();
  }

  /**
   * Busca un libro por su id mediante {@code GET /books/{bookId}}.
   *
   * @param bookId el id del libro
   * @return el {@link BookDto} encontrado y el estado 200 OK
   */
  @GET
  @Path("/{bookId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("bookId") Long bookId) {
    BookDto bookById = bookService.findById(bookId);
    return Response.ok(bookById).build();
  }

  /**
   * Crea un libro mediante {@code POST /books}.
   *
   * @param requestDto los datos del libro a crear
   * @return el estado 201 Created con la cabecera {@code Location} del libro creado
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response store(@Valid CreateBookRequestDto requestDto) {
    BookDto createdBook = bookService.create(requestDto);
    URI locationUri =
        UriBuilder.fromResource(BookResource.class)
            .path("/{bookId}")
            .resolveTemplate("bookId", createdBook.getId())
            .build();
    return Response.created(locationUri).build();
  }

  /**
   * Actualiza un libro mediante {@code PUT /books/{bookId}}.
   *
   * @param bookId el id del libro a actualizar
   * @param requestDto los nuevos datos del libro
   * @return el estado 204 No Content
   */
  @PUT
  @Path("/{bookId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("bookId") Long bookId, @Valid UpdateBookRequestDto requestDto) {
    bookService.update(bookId, requestDto);
    return Response.noContent().build();
  }

  /**
   * Elimina un libro mediante {@code DELETE /books/{bookId}}.
   *
   * @param bookId el id del libro a eliminar
   * @return el estado 204 No Content
   */
  @DELETE
  @Path("/{bookId}")
  public Response deleteById(@PathParam("bookId") Long bookId) {
    bookService.deleteById(bookId);
    return Response.noContent().build();
  }

  /**
   * Obtiene los autores de un libro mediante {@code GET /books/{bookId}/authors}.
   *
   * @param bookId el id del libro
   * @return la lista de {@link AuthorDto} del libro y el estado 200 OK
   */
  @GET
  @Path("/{bookId}/authors")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findBookAuthors(@PathParam("bookId") Long bookId) {
    List<AuthorDto> authors = bookService.findAuthorsByBookId(bookId);
    return Response.ok(authors).build();
  }
}
