package com.belentpatrus.gasstation.controller;

import com.belentpatrus.gasstation.model.inventory.DailyCashTracker;
import com.belentpatrus.gasstation.service.DailyCashTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class DailyCashTrackerController {

    private DailyCashTrackerService dailyCashTrackerService;

    @Autowired
    public DailyCashTrackerController(DailyCashTrackerService dailyCashTrackerService) {
        this.dailyCashTrackerService = dailyCashTrackerService;
    }

    @PostMapping("/cash/{dailyCashTrackerLog}")
    public boolean saveDailyCashTracker(@RequestBody DailyCashTracker dailyCashTrackerLog) {
        return dailyCashTrackerService.save(dailyCashTrackerLog);
    }
}
