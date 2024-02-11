package com.carlosarroyoam.book.service.resource;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carlosarroyoam.book.service.entity.Author;

@Path("/authors")
@ApplicationScoped
public class AuthorResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Author> authors = new ArrayList<>();
		return Response.ok(authors).build();
	}

}
