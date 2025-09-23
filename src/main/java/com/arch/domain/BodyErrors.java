package com.arch.domain;

import java.util.Set;

public class BodyErrors {

  private Set<String> errors;
  private String message;

  public Set<String> getErrors() {
    return errors;
  }

  public void setErrors(Set<String> errors) {
    this.errors = errors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Set<String> errors;
    private String message;

    public Builder errors(Set<String> errors) {
      this.errors = errors;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public BodyErrors build() {
      BodyErrors bodyErrors = new BodyErrors();
      bodyErrors.setErrors(errors);
      bodyErrors.setMessage(message);
      return bodyErrors;
    }
  }
}
