package com.belentpatrus.gasstation.controller;


import com.belentpatrus.gasstation.model.MerchandiseItemSale;
import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/consumer")
public class MerchandiseItemSaleExcelReaderController {

    @Autowired
    MerchandiseItemSaleExcelReaderService myService;

    @PostMapping("/consumer")
    public List<MerchandiseItemSale> readProductsFromExcel(String filePath) {
        return myService.readProductsFromExcel(filePath);
    }
}
