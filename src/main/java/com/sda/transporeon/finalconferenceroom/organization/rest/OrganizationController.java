package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
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
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getAllOrganizations());
    }

    @GetMapping("/{organizationName}")
    public ResponseEntity<OrganizationDto> getOrganizationByName(@PathVariable("organizationName") String organizationName) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOrganizationByName(organizationName));
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.addOrganization(organizationRequest));
    }

    @DeleteMapping("/delete/{organizationName}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("organizationName") String organizationName) {
        organizationService.deleteOrganization(organizationName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{organizationName}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable String organizationName, @RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.updateOrganization(organizationName, organizationRequest));
    }
}
