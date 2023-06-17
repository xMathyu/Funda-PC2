package com.dalbitresb.monitoringservice.requirement.api;

import com.dalbitresb.monitoringservice.requirement.resources.RequirementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/v1/monitoring")
public class RequirementController {
    private static String OS = System.getProperty("os.name").toLowerCase();

    @GetMapping("/availability")
    public ResponseEntity<String> checkAvailability() {
        return ResponseEntity.status(HttpStatus.OK).body("Service is available");
    }

    @GetMapping("/info")
    public ResponseEntity<RequirementResponse> getMonitoringInfo() {
        long start = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        int cpuCount = runtime.availableProcessors();
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;

        File rootDrive = new File(OS.contains("win") ? "C:" : "/");
        double totalSpace = (double) (rootDrive.getTotalSpace() / 1073741824);
        double freeSpace = (double) (rootDrive.getFreeSpace() / 1073741824);

        RequirementResponse response = new RequirementResponse();
        response.setCpuCount(cpuCount);
        response.setMaxMemory(maxMemory);
        response.setTotalMemory(totalMemory);
        response.setFreeMemory(freeMemory);
        response.setTotalSpace(totalSpace);
        response.setFreeSpace(freeSpace);

        double elapsedTime = (double) (System.nanoTime() - start) / 1_000_000_000;
        response.setElapsedTime(elapsedTime);

        return ResponseEntity.ok(response);
    }
}
