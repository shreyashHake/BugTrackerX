package com.springBoot.eBugTracker.dtos.bugs;

import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BugProcessDTO {
    private int bugProcessId;
    private String GlobalStatus;
    private StaffProfileDTO staffProfile;
    private BugDTO bug;
}
