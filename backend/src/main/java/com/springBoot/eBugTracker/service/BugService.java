package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.dtos.bugs.BugDetailsDTO;
import com.springBoot.eBugTracker.dtos.bugs.CommentDTO;
import com.springBoot.eBugTracker.entity.bugs.Comment;

public interface BugService {
    String assignStaff(int bugProcessId, int staffProfileId);

    CommentDTO addComment(Comment comment);

    BugDetailsDTO getBugDetails(int bugId);

    void getTest(int id);

    String changeBugStatus(int bugId, String bugStatus);

    String changeGlobalBugStatus(int bugProcessId, String globalBugStatus);
}
