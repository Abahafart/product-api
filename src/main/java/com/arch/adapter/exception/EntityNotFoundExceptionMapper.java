package com.arch.adapter.exception;

import com.arch.domain.BodyErrors;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

  @Override
  public Response toResponse(EntityNotFoundException exception) {
    BodyErrors bodyErrors = BodyErrors.builder().message(exception.getMessage()).build();
    return Response.status(Response.Status.NOT_FOUND)
        .entity(bodyErrors).build();
  }
}
