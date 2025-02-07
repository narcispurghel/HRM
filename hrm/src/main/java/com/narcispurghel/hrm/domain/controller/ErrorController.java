package com.narcispurghel.hrm.domain.controller;

import com.narcispurghel.hrm.domain.dto.ErrorMessageDto;
import com.narcispurghel.hrm.domain.exception.DuplicateUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>("Error occurred " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorMessageDto> handleException(DuplicateUsernameException ex) {
        ErrorMessageDto response = new ErrorMessageDto(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        return new ResponseEntity<ErrorMessageDto>(response, HttpStatus.CONFLICT);
    }
}
