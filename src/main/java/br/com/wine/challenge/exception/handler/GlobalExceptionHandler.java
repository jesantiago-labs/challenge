package br.com.wine.challenge.exception.handler;

import br.com.wine.challenge.dto.error.response.ErrorResponseDTO;
import br.com.wine.challenge.exception.BusinessException;
import br.com.wine.challenge.exception.ConflictException;
import br.com.wine.challenge.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> exception(HttpMessageNotReadableException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> conflictException(ConflictException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldErrors().get(0);

        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(fieldError.getField() + " " + fieldError.getDefaultMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> businessException(BusinessException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exception(Exception ex) {
        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
