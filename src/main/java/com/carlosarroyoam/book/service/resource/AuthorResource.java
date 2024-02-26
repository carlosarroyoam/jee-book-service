package com.carlosarroyoam.book.service.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carlosarroyoam.book.service.dto.AuthorResponse;
import com.carlosarroyoam.book.service.service.AuthorService;

@Path("/authors")
@ApplicationScoped
public class AuthorResource {

	@Inject
	private AuthorService authorService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<AuthorResponse> authors = authorService.findAll();
		return Response.ok(authors).build();
	}

	@GET
	@Path("/{authorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByIsbn(@PathParam("authorId") Long authorId) {
		AuthorResponse authorById = authorService.findById(authorId);
		return Response.ok(authorById).build();
	}

}
