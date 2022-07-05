package com.devsuperior.dslearn.controller.exception;

import com.devsuperior.dslearn.exceptions.DataBaseException;
import com.devsuperior.dslearn.exceptions.ForbbidenException;
import com.devsuperior.dslearn.exceptions.ResourceNotFoundException;
import com.devsuperior.dslearn.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> databaseError(HttpServletRequest request, DataBaseException e){
        StandardError err = createStandardError(request, e);
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> emptyResult(HttpServletRequest request, ResourceNotFoundException e) {
        StandardError err = createStandardError(request, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> usernameNotFound(HttpServletRequest request, ResourceNotFoundException e) {
        StandardError err = createStandardError(request, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(HttpServletRequest request, MethodArgumentNotValidException e) {
        ValidationError err = createValidationError(request, e);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(ForbbidenException.class)
    public ResponseEntity<AuthorizationError> forbidden(HttpServletRequest request, ForbbidenException e) {
        AuthorizationError err = new AuthorizationError( "Forbidden", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<AuthorizationError> forbidden(HttpServletRequest request, UnauthorizedException e) {
        AuthorizationError err = new AuthorizationError( "Unauthorized", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    private ValidationError createValidationError(HttpServletRequest request, MethodArgumentNotValidException e) {
        ValidationError err = new ValidationError();
        err.setMessage(e.getMessage());
        err.setError("Validation Error");
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        err.setTimestamp(Instant.now());
        err.setPath(request.getRequestURI());
        e.getBindingResult()
                .getFieldErrors()
                .forEach(x -> err.getErrors()
                        .add(new FieldError(x.getField(), x.getDefaultMessage())));
        return err;
    }

    private StandardError createStandardError(HttpServletRequest request, RuntimeException e) {
        StandardError err = new StandardError();
        err.setMessage(e.getMessage());
        err.setError("Error");
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(Instant.now());
        err.setPath(request.getRequestURI());
        return err;
    }
}
