package com.carlosarroyoam.library.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carlosarroyoam.library.model.Author;

@Path("/authors")
@RequestScoped
public class AuthorResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Author> authors = new ArrayList<>();

		authors.add(new Author(1L, "Kapila Bogahapitiya"));
		authors.add(new Author(2L, "Douglas Adams"));

		return Response.ok(authors).build();
	}
}
