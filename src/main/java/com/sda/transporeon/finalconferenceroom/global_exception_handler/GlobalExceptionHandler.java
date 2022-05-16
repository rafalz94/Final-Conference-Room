package com.sda.transporeon.finalconferenceroom.global_exception_handler;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ErrorBody handleOrganizationNotFoundException(final OrganizationNotFoundException e) {
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrganizationAlreadyExistsException.class)
    public ErrorBody handleOrganizationAlreadyExistsException(final OrganizationAlreadyExistsException e) {
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConferenceRoomNotFoundException.class)
    public ErrorBody handleConferenceRoomNotFoundException(final ConferenceRoomNotFoundException e) {
        return new ErrorBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConferenceRoomAlreadyExistsException.class)
    public ErrorBody handleConferenceRoomAlreadyExistsException(final ConferenceRoomAlreadyExistsException e) {
        return new ErrorBody(e.getMessage());
    }

}
