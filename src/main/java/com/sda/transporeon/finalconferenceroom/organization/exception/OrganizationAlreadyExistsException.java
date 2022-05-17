package com.sda.transporeon.finalconferenceroom.organization.exception;

public class OrganizationAlreadyExistsException extends IllegalArgumentException {

    public OrganizationAlreadyExistsException(String organization) {
        super(String.format("Organization %s already exists.", organization));
    }
}
