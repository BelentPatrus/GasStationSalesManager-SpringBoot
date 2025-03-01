package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.MerchandiseItemSale;
import jakarta.annotation.PostConstruct;
import lombok.val;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;


@Service
public class MerchandiseItemSaleExcelReader {

    private final int MAX_COLUMNS = 22;

    public List<MerchandiseItemSale> readProductsFromExcel(String filePath) {
        List<MerchandiseItemSale> products = new ArrayList<MerchandiseItemSale>();
        boolean isData = false;
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook wb = WorkbookFactory.create(fis);) {

            Sheet sheet = wb.getSheetAt(0); // Assuming data is in the first sheet
            for (Row row : sheet) {
                if(row.getRowNum() <= 12 ) continue;
                boolean hitUPC = false;
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().matches(".*[a-zA-Z].*") && !hitUPC)
                        break;

                    else if(cell.getStringCellValue().length() == 11){
                        hitUPC = true;
                        String upc = cell.getStringCellValue();
//
//                    String upc = row.getCell(0).getStringCellValue();
//                    int number = (int) row.getCell(1).getNumericCellValue();
//                    String description = row.getCell(2).getStringCellValue();
//                    String packageDescription = row.getCell(3).getStringCellValue();
//                    int packageQuantity = (int) row.getCell(4).getNumericCellValue();
//                    int quantitySold = (int) row.getCell(5).getNumericCellValue();
//                    double unitRetail = row.getCell(6).getNumericCellValue();
//                    double extendedRetail = row.getCell(7).getNumericCellValue();

//                    MerchandiseItemSale product = new MerchandiseItemSale(upc, number, description, packageDescription, packageQuantity, quantitySold, unitRetail, extendedRetail);
                    }
//                    products.add(product);
                }
            }
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}