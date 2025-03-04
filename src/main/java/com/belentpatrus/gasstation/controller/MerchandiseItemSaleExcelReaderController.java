package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.service.DailyMerchandiseSalesSummaryService;
import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
        return myService.readProductsFromExcel("C:/Users/belent/Documents/Daily Merchandise Item Sales Reports/test.xls");
    }

    @GetMapping("/consumer/{date}")
    public DailyMerchandiseSales getSummary(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return dailyMerchandiseSalesSummaryService.getDailyMerchandiseSales(localDate);
    }
}
