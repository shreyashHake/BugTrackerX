package com.springBoot.eBugTracker.repository.bugs;

import com.springBoot.eBugTracker.entity.bugs.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
