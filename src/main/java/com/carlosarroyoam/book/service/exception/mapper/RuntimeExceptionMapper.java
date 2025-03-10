package com.carlosarroyoam.book.service.exception.mapper;

import com.carlosarroyoam.book.service.dto.AppExceptionDto;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
  @Context
  private UriInfo uriInfo;

  @Override
  public Response toResponse(RuntimeException ex) {
    Status status = Status.INTERNAL_SERVER_ERROR;

    AppExceptionDto appExceptionDto = AppExceptionDto.builder()
        .message("Whoops! Something went wrong")
        .code(status.getStatusCode())
        .status(status.getReasonPhrase())
        .path(uriInfo.getPath())
        .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
        .build();

    ex.printStackTrace();

    return Response.status(status).entity(appExceptionDto).type(MediaType.APPLICATION_JSON).build();
  }
}
