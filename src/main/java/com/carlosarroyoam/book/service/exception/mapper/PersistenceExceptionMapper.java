package com.carlosarroyoam.book.service.exception.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.carlosarroyoam.book.service.dto.APIErrorDto;

/**
 * An {@link ExceptionMapper} implementation for all JPA
 * {@link PersistenceException}s.
 */
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
	@Context
	private UriInfo uriInfo;

	@Override
	public Response toResponse(PersistenceException exception) {
		if (exception instanceof EntityNotFoundException) {
			APIErrorDto apiErrorDto = new APIErrorDto();
			Status status = Status.NOT_FOUND;

			apiErrorDto.setMessage(exception.getMessage());
			apiErrorDto.setError(status.getReasonPhrase());
			apiErrorDto.setStatus(status.getStatusCode());
			apiErrorDto.setPath(uriInfo.getPath());
			apiErrorDto.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).withFixedOffsetZone());

			return Response.status(status).entity(apiErrorDto).type(MediaType.APPLICATION_JSON).build();
		}

		throw exception;
	}
}
