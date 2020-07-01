package ru.darzam.payservice.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author zamaliev
 */
@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {

  private static final String ERRORS = "errors";
  private static final String STATUS = "status";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Map<String, Object> body = new HashMap<>();

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(field -> field.getField() + " " + field.getDefaultMessage())
        .collect(Collectors.toList());

    body.put(STATUS, status.value());
    body.put(ERRORS, errors);

    return new ResponseEntity<>(body, headers, status);

  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public ResponseEntity<Object> invalidArgument(RuntimeException ex){
    Map<String, Object> message = new HashMap<>();
    HttpStatus status = HttpStatus.BAD_REQUEST;

    if (ex.getClass().isAssignableFrom(EntityNotFoundException.class)) {
      status = HttpStatus.NOT_FOUND;
    }
    message.put(STATUS, status.value());
    message.put(ERRORS, ex.getMessage());

    return new ResponseEntity<>(message, status);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    Map<String, Object> message = new HashMap<>();

    message.put(STATUS, status.value());
    message.put(ERRORS, "Application error call to administrator");

    return new ResponseEntity<>(message, headers, status);
  }
}
