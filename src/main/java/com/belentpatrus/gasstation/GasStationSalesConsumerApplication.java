package com.belentpatrus.gasstation;

import com.belentpatrus.gasstation.service.MerchandiseItemSaleExcelReaderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GasStationSalesConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GasStationSalesConsumerApplication.class, args);

        // Get your bean
        MerchandiseItemSaleExcelReaderService myService = context.getBean(MerchandiseItemSaleExcelReaderService.class);

        // Use the bean directly
        myService.readProductsFromExcel("C:/Users/billy/Documents/Daily Merchandise Item Sales Reports/test.xls");
    }

}
