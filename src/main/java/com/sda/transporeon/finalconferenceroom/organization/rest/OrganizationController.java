package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import com.sda.transporeon.finalconferenceroom.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getAllOrganizations());
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponse> getOrganizationByName(@PathVariable("organizationId") Long organizationId) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOrganizationById(organizationId));
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationResponse> addOrganization(@RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.addOrganization(organizationRequest));
    }

    @DeleteMapping("/delete/{organizationId}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("organizationId") Long organizationId) {
        organizationService.deleteOrganization(organizationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<OrganizationResponse> updateOrganization(@RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.updateOrganization(organizationRequest));
    }
}
