package com.sda.transporeon.finalconferenceroom.organization.exception;

import java.util.NoSuchElementException;

public class OrganizationNotFoundException extends NoSuchElementException {

    public OrganizationNotFoundException() {
        super("Organization not found.");
    }
}
