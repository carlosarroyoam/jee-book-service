package com.carlosarroyoam.library.boundary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import com.carlosarroyoam.library.model.Author;
import com.carlosarroyoam.library.model.Book;
import com.carlosarroyoam.library.service.BookService;

@Path("books")
@RequestScoped
public class BookResource {
	@Inject
	private BookService bookService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		return Response.ok(bookService.findAll()).build();
	}

	@GET
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByIsbn(@PathParam("isbn") String isbn) {
		return Response.ok(bookService.findByIsbn(isbn)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response store(Book book) {
		Book createdBook = bookService.store(book);

		URI location = UriBuilder.fromResource(BookResource.class).path("/{isbn}")
				.resolveTemplate("isbn", createdBook.getIsbn()).build();

		return Response.created(location).build();
	}

	@PUT
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("isbn") String isbn, Book book) {
		Book updatedBook = bookService.update(book, isbn);

		return Response.ok(updatedBook).build();
	}

	@DELETE
	@Path("/{isbn}")
	public Response delete(@PathParam("isbn") String isbn) {
		bookService.deleteByIsbn(isbn);

		return Response.noContent().build();
	}

	@GET
	@Path("/{isbn}/authors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findBookAuthors(@PathParam("isbn") String isbn) {
		List<Author> authors = new ArrayList<>();

		authors.add(new Author(1L, "Kapila Bogahapitiya"));

		return Response.ok(authors).build();
	}
}
