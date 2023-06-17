package com.dalbitresb.monitoringservice.requirement.resources;

import lombok.Data;

@Data
public class RequirementResponse {
    private int cpuCount;
    private long maxMemory;
    private long totalMemory;
    private long freeMemory;
    private double elapsedTime;
    private double totalSpace;
    private double freeSpace;
}
