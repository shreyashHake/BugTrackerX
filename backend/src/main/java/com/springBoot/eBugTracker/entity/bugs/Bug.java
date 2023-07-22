package com.springBoot.eBugTracker.entity.bugs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bugId;
    private String bugTitle;
    private String bugDesc;
    private String bugStatus;
    private String bugPriority;
    @JsonIgnore
    private LocalDate createdDate;
    @JsonIgnore
    private LocalDate endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId", nullable = false, referencedColumnName = "projectId")
    private CustomerProject customerProject;
}
