package com.carlosarroyoam.library.exception.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.carlosarroyoam.library.dto.APIErrorDto;

/**
 * An {@link ExceptionMapper} implementation for all JPA
 * {@link PersistenceException}s.
 */
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
	@Override
	public Response toResponse(PersistenceException exception) {
		APIErrorDto apiErrorDto = new APIErrorDto();

		if (exception instanceof EntityNotFoundException) {
			apiErrorDto.setMessage(exception.getMessage());
			apiErrorDto.setError(Status.NOT_FOUND.getReasonPhrase());
			apiErrorDto.setStatus(Status.NOT_FOUND.getStatusCode());
			apiErrorDto.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.MILLIS));

			return Response.status(Status.NOT_FOUND).entity(apiErrorDto).type(MediaType.APPLICATION_JSON).build();
		}

		throw exception;
	}
}
