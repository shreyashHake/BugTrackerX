package com.springBoot.eBugTracker.dtos.bugs;

import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BugDTO {
    private int bug_id;
    private String bug_title;
    private String bug_desc;
    private String bug_status;
    private String bug_priority;
    private LocalDate createdDate;
    private LocalDate endDate;
//    private CustomerProjectDTO customerProject;
}
