package ru.veqveq.conference.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ConferenceError error = new ConferenceError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleChangeRoleException(ChangeRoleException e) {
        ConferenceError error = new ConferenceError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTimeIntervalException(TimeIntervalException e) {
        ConferenceError error = new ConferenceError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTimeIntervalIntersectionException(TimeIntervalIntersectionException e) {
        ConferenceError error = new ConferenceError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIncorrectOwnerException(IncorrectOwnerException e) {
        ConferenceError error = new ConferenceError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
