package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.dailysales.enums.Department;
import com.belentpatrus.gasstation.service.DailyMerchandiseSalesSummaryService;
import com.belentpatrus.gasstation.service.SyncService;
import com.belentpatrus.gasstation.service.dto.DailyMerchandiseSalesSummaryDTO;
import com.belentpatrus.gasstation.service.dto.SyncDailyMerchandiseSalesAndProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/consumer")
@RestController()
public class DailyMerchandiseSalesController {


    private DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService;
    private SyncService syncService;

    @Autowired
    public DailyMerchandiseSalesController(DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService, SyncService syncService) {
        this.dailyMerchandiseSalesSummaryService = dailyMerchandiseSalesSummaryService;
        this.syncService = syncService;

    }

    @GetMapping("/{date}")
    public DailyMerchandiseSalesSummaryDTO getSummary(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return dailyMerchandiseSalesSummaryService.getDailyMerchandiseSales(localDate);
    }

    @GetMapping("/{id}/{department}")
    public double getSummary(@PathVariable long id, @PathVariable Department department) {
        return dailyMerchandiseSalesSummaryService.getTotalSoldByDepartment(id, department);
    }

    @GetMapping("/getsummary/{id}")
    public DailyMerchandiseSalesSummaryDTO getSummary(@PathVariable long id) {
        return dailyMerchandiseSalesSummaryService.getDailyMerchandiseSalesSummary(id);
    }

    @GetMapping("/sync/{date}")
    public SyncDailyMerchandiseSalesAndProductDTO returnNotSynced(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        DailyMerchandiseSalesSummaryDTO dailyMerchandiseSalesSummaryDTO = dailyMerchandiseSalesSummaryService.getDailyMerchandiseSales(localDate);
        return syncService.notSyncedMerchandiseItemSales(dailyMerchandiseSalesSummaryDTO);

    }



}
