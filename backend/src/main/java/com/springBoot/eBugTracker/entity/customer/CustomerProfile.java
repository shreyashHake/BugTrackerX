package com.springBoot.eBugTracker.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springBoot.eBugTracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerProfile  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerProfileId;
    private String customerName;
    private String customerCompany;
    private String customerPhone;
    private Boolean isActive;
    @JsonIgnore
    private LocalDate createdDate;
    @OneToOne
    @JoinColumn(name = "userName", nullable = false, referencedColumnName = "userName")
    private User user;
}
//    @OneToMany
//    @JoinColumn(name = "customerProjectId",nullable = true,referencedColumnName = "customerProjectId")
//    private List<CustomerProject> customerProjects;

