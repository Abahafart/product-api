package com.arch.adapter.exception;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.engine.path.PathImpl;

import com.arch.domain.BodyErrors;
import com.arch.domain.ExceptionConstants;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements
    ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    Set<String> errors = exception.getConstraintViolations()
        .stream()
        .map(e -> ((PathImpl) e.getPropertyPath()).getLeafNode() + ": " + e.getMessage())
        .collect(Collectors.toSet());
    BodyErrors bodyErrors = BodyErrors.builder().message(ExceptionConstants.INVALID_REQUEST_CONTENT).errors(errors).build();
    return Response.status(Response.Status.BAD_REQUEST).entity(bodyErrors).build();
  }
}
