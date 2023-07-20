package com.springBoot.eBugTracker.entity.bugs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springBoot.eBugTracker.entity.User;
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
    private String commentBy;
    @Column(columnDefinition = "TIMESTAMP")
    @JsonIgnore
    private LocalDateTime commentDateTime;
    @OneToOne
    @JoinColumn(name = "userName", nullable = false, referencedColumnName = "userName")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bugProcessId", nullable = false)
    private BugProcess bugProcess;

}
