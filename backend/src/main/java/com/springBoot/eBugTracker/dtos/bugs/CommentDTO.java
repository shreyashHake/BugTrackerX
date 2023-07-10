package com.springBoot.eBugTracker.dtos.bugs;

import com.springBoot.eBugTracker.dtos.customer.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int commentId;
    private int commentNumber;
    private String comment;
    private String commentBy;
    private LocalDateTime commentDateTime;
    private UserDTO user;
//    private BugProcessDTO bugProcess;
}
