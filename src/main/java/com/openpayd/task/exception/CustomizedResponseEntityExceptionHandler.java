package com.openpayd.task.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.openpayd.task.dto.Response;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<Response> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
    ex.printStackTrace();
    Response errorDetails = new Response("404", ex.getMessage(), new Date());
    errorDetails.setDetails(request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

 
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Response> handleUserNotFoundException(Exception ex, WebRequest request) {
    ex.printStackTrace();
    Response errorDetails = new Response("500", ex.getMessage(), new Date());
    errorDetails.setDetails(request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
