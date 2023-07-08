package com.springBoot.eBugTracker.entity.staff;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffProfile
{
    @Id
    @GeneratedValue
    private int staffProfileId;
    private String staffName;
    private String staffPhone;
    private String staffEmail;
    private Boolean isActive;
    @JsonIgnore
    private LocalDate createdDate;
    @OneToOne
    @JoinColumn(name = "userName", nullable = false, referencedColumnName = "userName")
    private User user;

}
