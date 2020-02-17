package com.scalefocus.java.exception;

import com.scalefocus.java.exception.models.ExceptionResponse;
import com.scalefocus.java.exception.models.ValidationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public static ResponseEntity<ValidationExceptionResponse> handleValidationException(ValidationException ex) {
    ValidationExceptionResponse validationExeptionResponse =
        new ValidationExceptionResponse(ex.getFieldErrors());
    return new ResponseEntity<>(validationExeptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AlreadyExistException.class)
  public static ResponseEntity<ExceptionResponse> handleAlreadyExistException(AlreadyExistException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NotFoundException.class)
  public static ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GeneralException.class)
  public static ResponseEntity<ExceptionResponse> handleNotFoundException(GeneralException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
}
