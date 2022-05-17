package com.sda.transporeon.finalconferenceroom.organization.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class OrganizationRequest {

    private Long organizationId;
    @NotBlank(message = "Organization name cannot be blank!", groups = AddOrganizationGroup.class)
    @Size(min = 2, max = 20, message = "Organization name has to be between 2 and 20 characters!", groups = {AddOrganizationGroup.class, UpdateOrganizationGroup.class})
    private String organizationName;
}
