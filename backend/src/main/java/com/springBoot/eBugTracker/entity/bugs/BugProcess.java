package com.springBoot.eBugTracker.entity.bugs;

import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BugProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bugProcessId;
    private String GlobalStatus;
    @OneToOne
    @JoinColumn(name = "staffProfileId", referencedColumnName = "staffProfileId")
    private StaffProfile staffProfile;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BugId", nullable = false, referencedColumnName = "BugId")
    private Bug bug;


}
