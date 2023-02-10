package ru.gb.market.exceptioms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public ResponseEntity<AppError> catchNonFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new AppError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AppError> catchUsernameNotFoundException (UsernameNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new AppError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AppError> catchValidationErrorException (ValidationErrorException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new AppError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
