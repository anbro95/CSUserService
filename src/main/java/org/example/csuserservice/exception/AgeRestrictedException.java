package org.example.csuserservice.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class AgeRestrictedException extends ResponseStatusException {

  public AgeRestrictedException(String message) {
    super(BAD_REQUEST, message);
  }
}
