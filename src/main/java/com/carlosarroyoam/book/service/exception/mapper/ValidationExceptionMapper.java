package com.carlosarroyoam.book.service.exception.mapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.carlosarroyoam.book.service.dto.AppExceptionResponse;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Context
	private UriInfo uriInfo;

	@Override
	public Response toResponse(ValidationException ex) {
		Status status = Status.BAD_REQUEST;

		AppExceptionResponse appExceptionResponse = new AppExceptionResponse();
		appExceptionResponse.setMessage("Request data is not valid");
		appExceptionResponse.setCode(status.getStatusCode());
		appExceptionResponse.setStatus(status.getReasonPhrase());
		appExceptionResponse.setPath(uriInfo.getPath());
		appExceptionResponse.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).withFixedOffsetZone());

		return Response.status(status).entity(appExceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}

}
