package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/organization")
public class OrganizationController {


    @PostMapping
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody @Valid final OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrganizationDto());
    }

}
