package com.springBoot.eBugTracker.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProjectDTO {
    private int projectId;
    private String projectName;
    private String projectDomain;
    private String projectDesc;
    private String projectStatus;
    private LocalDate createdDate;
    private LocalDate endDate;
    private CustomerProfileDTO customerProfile;
}
