package com.springBoot.eBugTracker.service.bug;

import com.springBoot.eBugTracker.dtos.bugs.CommentDTO;
import com.springBoot.eBugTracker.entity.bugs.Comment;

public interface BugService {
    String assignStaff(int bugProcessId, int staffProfileId);

    CommentDTO addComment(Comment comment);
}
