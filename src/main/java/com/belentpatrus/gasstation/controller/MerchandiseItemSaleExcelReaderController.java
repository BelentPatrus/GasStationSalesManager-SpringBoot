package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.model.dailysales.enums.Department;
import com.belentpatrus.gasstation.service.DailyMerchandiseSalesSummaryService;
import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import com.belentpatrus.gasstation.service.SyncService;
import com.belentpatrus.gasstation.service.dto.DailyMerchandiseSalesSummaryDTO;
import com.belentpatrus.gasstation.service.dto.SyncDailyMerchandiseSalesAndProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController("/consumer")
public class MerchandiseItemSaleExcelReaderController {


    private DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService;
    private SyncService syncService;

    @Autowired
    public MerchandiseItemSaleExcelReaderController(DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService, SyncService syncService) {
        this.dailyMerchandiseSalesSummaryService = dailyMerchandiseSalesSummaryService;
        this.syncService = syncService;

    }

    @GetMapping("/consumer/{date}")
    public DailyMerchandiseSalesSummaryDTO getSummary(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return dailyMerchandiseSalesSummaryService.getDailyMerchandiseSales(localDate);
    }

    @GetMapping("/consumer/{id}/{department}")
    public double getSummary(@PathVariable long id, @PathVariable Department department) {
        return dailyMerchandiseSalesSummaryService.getTotalSoldByDepartment(id, department);
    }

    @GetMapping("/consumer/getsummary/{id}")
    public DailyMerchandiseSalesSummaryDTO getSummary(@PathVariable long id) {
        return dailyMerchandiseSalesSummaryService.getDailyMerchandiseSalesSummary(id);
    }

    @GetMapping("/consumer/sync/{date}")
    public SyncDailyMerchandiseSalesAndProductDTO returnNotSynced(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        DailyMerchandiseSalesSummaryDTO dailyMerchandiseSalesSummaryDTO = dailyMerchandiseSalesSummaryService.getDailyMerchandiseSales(localDate);
        return syncService.notSyncedMerchandiseItemSales(dailyMerchandiseSalesSummaryDTO);

    }



}
