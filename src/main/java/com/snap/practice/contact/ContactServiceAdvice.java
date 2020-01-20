package com.snap.practice.contact;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ContactServiceAdvice {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(ContactServiceAdvice.class);

    @ExceptionHandler({ContactNotFoundException.class})
    public ResponseEntity<String> handleRunTimeException(ContactNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("NOT_FOUND",e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
