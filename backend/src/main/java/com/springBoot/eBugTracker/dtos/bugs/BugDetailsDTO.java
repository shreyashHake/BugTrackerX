package com.springBoot.eBugTracker.dtos.bugs;

import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BugDetailsDTO {
    private int bug_id;
    private String bug_title;
    private String bug_desc;
    private String bug_status;
    private String bug_priority;
    private LocalDate createdDate;
    private LocalDate endDate;

    private int bugProcessId;
    private String GlobalStatus;
//    private StaffProfileDTO staffProfile;
    private List<CommentDTO> comments;
}
