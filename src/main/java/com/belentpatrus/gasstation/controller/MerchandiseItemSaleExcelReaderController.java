package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/consumer")
public class MerchandiseItemSaleExcelReaderController {

    @Autowired
    MerchandiseItemSaleExcelReaderService myService;

    @GetMapping("/consumer")
    public List<MerchandiseItemSale> readProductsFromExcel() {
        return myService.readProductsFromExcel("C:/Users/billy/Documents/Daily Merchandise Item Sales Reports/test.xls");
    }
}
