package com.carlosarroyoam.library.exception.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.carlosarroyoam.library.dto.APIErrorDto;

/**
 * An {@link ExceptionMapper} implementation for all
 * {@link WebApplicationException}s.
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException exception) {
		APIErrorDto apiErrorDto = new APIErrorDto();

		apiErrorDto.setMessage(exception.getMessage());
		apiErrorDto.setError(exception.getResponse().getStatusInfo().getReasonPhrase());
		apiErrorDto.setStatus(exception.getResponse().getStatusInfo().getStatusCode());
		apiErrorDto.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.MILLIS));

		return Response.status(exception.getResponse().getStatusInfo().getStatusCode()).entity(apiErrorDto)
				.type(MediaType.APPLICATION_JSON).build();
	}
}
