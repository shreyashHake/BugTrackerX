package com.springBoot.eBugTracker.entity.bugs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int commentNumber;
    private String comment;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime commentDateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bugProcessId",nullable = false)
    private BugProcess bugProcess;

}
