package ru.gb.market.exceptioms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public ResponseEntity<NotFoundError> catchNonFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new NotFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<NotFoundError> catchUsernameNotFoundException (UsernameNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new NotFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
