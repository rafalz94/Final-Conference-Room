package com.sda.transporeon.finalconferenceroom.organization.exception;

import java.util.NoSuchElementException;

public class OrganizationNotFoundException extends NoSuchElementException {

    public OrganizationNotFoundException(String organization) {
        super(String.format("Organization %s not found.", organization));
    }
}
