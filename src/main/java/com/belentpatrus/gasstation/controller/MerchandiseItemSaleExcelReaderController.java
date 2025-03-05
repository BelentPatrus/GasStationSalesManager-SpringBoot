package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.enums.Department;
import com.belentpatrus.gasstation.service.DailyMerchandiseSalesSummaryService;
import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import com.belentpatrus.gasstation.service.dto.DailyMerchandiseSalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("/consumer")
public class MerchandiseItemSaleExcelReaderController {


    private MerchandiseItemSaleExcelReaderService myService;
    private DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService;

    @Autowired
    public MerchandiseItemSaleExcelReaderController(MerchandiseItemSaleExcelReaderService myService, DailyMerchandiseSalesSummaryService dailyMerchandiseSalesSummaryService) {
        this.myService = myService;
        this.dailyMerchandiseSalesSummaryService = dailyMerchandiseSalesSummaryService;
    }
    @GetMapping("/consumer")
    public DailyMerchandiseSales readProductsFromExcel() {
        return myService.readProductsFromExcel("C:/Users/billy/Documents/Daily Merchandise Item Sales Reports/test.xls");
    }

    @GetMapping("/consumer/{date}")
    public DailyMerchandiseSales getSummary(@PathVariable String date) {
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

}
