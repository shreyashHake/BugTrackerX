package com.springBoot.eBugTracker.dtos.staff;

import com.springBoot.eBugTracker.dtos.customer.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffProfileDTO {
    private int staffProfileId;
    private String staffName;
    private String staffPhone;
    private String staffEmail;
    private Boolean isActive;
    private LocalDate createdDate;
//    private UserDTO user;
}
