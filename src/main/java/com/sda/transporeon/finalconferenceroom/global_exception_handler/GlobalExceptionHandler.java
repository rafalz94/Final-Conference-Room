package com.sda.transporeon.finalconferenceroom.global_exception_handler;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationNotFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ErrorBody handleOrganizationNotFoundException(final OrganizationNotFoundException e) {
        log.error("Organization not found.");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrganizationAlreadyExistsException.class)
    public ErrorBody handleOrganizationAlreadyExistsException(final OrganizationAlreadyExistsException e) {
        log.error("Organization already exists.");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public ErrorBody handleConferenceRoomNotFoundException(final ConferenceRoomNotFoundException e) {
        log.error("Conference room not found.");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConferenceRoomAlreadyExistsException.class)
    public ErrorBody handleConferenceRoomAlreadyExistsException(final ConferenceRoomAlreadyExistsException e) {
        log.error("Conference room already exists.");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ErrorBody handleReservationNotFoundException(final ReservationNotFoundException e) {
        log.error("Reservation not found");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyExistsException.class)
    public ErrorBody handleReservationAlreadyExistsException(final ReservationAlreadyExistsException e) {
        log.error("Reservation already exists");
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorBody handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        final StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : errors) {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        }
        return new ErrorBody(errorMessage.toString());
    }

}
