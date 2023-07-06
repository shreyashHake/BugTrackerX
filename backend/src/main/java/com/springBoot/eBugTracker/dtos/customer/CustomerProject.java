package com.springBoot.eBugTracker.dtos.customer;

public record CustomerProject (
        String projectName,
        String projectDomain,
        String projectDesc,
        String projectPriority,
        String userName) {
}
