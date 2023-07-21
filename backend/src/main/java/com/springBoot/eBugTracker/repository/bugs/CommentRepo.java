package com.springBoot.eBugTracker.repository.bugs;

import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.bugs.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByBugProcess(BugProcess bugProcess);
}
