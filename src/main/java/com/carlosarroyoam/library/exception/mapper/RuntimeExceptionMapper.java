package com.carlosarroyoam.library.exception.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.carlosarroyoam.library.dto.APIErrorDto;

/**
 * An {@link ExceptionMapper} implementation for all {@link RuntimeException}s.
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
	@Override
	public Response toResponse(RuntimeException exception) {
		APIErrorDto apiErrorDto = new APIErrorDto();

		apiErrorDto.setMessage(
				"The server encountered an unexpected condition that prevents it from completing the request");
		apiErrorDto.setError(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
		apiErrorDto.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		apiErrorDto.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.MILLIS));

		exception.printStackTrace();

		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(apiErrorDto).type(MediaType.APPLICATION_JSON)
				.build();
	}
}
