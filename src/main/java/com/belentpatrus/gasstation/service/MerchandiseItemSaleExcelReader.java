package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.MerchandiseItemSale;
import jakarta.annotation.PostConstruct;
import lombok.val;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.poi.ss.usermodel.*;


@Service
public class MerchandiseItemSaleExcelReader {

    private static final int UPC_COLUMN_INDEX = 4;
    private static final int NUMBER_COLUMN_INDEX = 5;
    private static final int DESCRIPTION_COLUMN_INDEX = 8;
    private static final int PACKAGE_DESCRIPTION_COLUMN_INDEX = 12;
    private static final int PACKAGE_QUANTITY_COLUMN_INDEX = 14;
    private static final int QUANTITY_SOLD_COLUMN_INDEX = 16;
    private static final int UNIT_RETAIL_COLUMN_INDEX = 17;
    private static final int EXTENDED_RETAIL_COLUMN_INDEX = 19;

//    private static final Map<Integer, Consumer<Cell>> stringHandlers = new HashMap<>();
//    private static final Map<Integer, Consumer<Cell>> numericHandlers = new HashMap<>();

    /**
     * Reads an Excel file and returns a list of MerchandiseItemSale objects.
     * It assumes that the Excel file has a header row, and that the columns
     * are in the order of UPC, Number, Description, Package Description,
     * Package Quantity, Quantity Sold, Unit Retail, Extended Retail.
     * The method will skip the first 12 rows of the file, as they are
     * assumed to be headers.
     * @param filePath the path to the Excel file
     * @return a list of MerchandiseItemSale objects
     */
    public List<MerchandiseItemSale> readProductsFromExcel(String filePath) {
        List<MerchandiseItemSale> products = new ArrayList<MerchandiseItemSale>();
        boolean isData = false;
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0); // Assuming data is in the first sheet
            boolean skipRow = false;
            for (Row row : sheet) {
                if(row.getRowNum() <= 12) continue;
                Iterator<Cell> cellIterator = row.cellIterator();
                boolean dataRow = false;
                MerchandiseItemSale product = new MerchandiseItemSale();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            if (!dataRow) {
                                skipRow = true;
                                break;
                            }   else if (cell.getColumnIndex() == DESCRIPTION_COLUMN_INDEX) {
                                product.setDescription(cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == PACKAGE_DESCRIPTION_COLUMN_INDEX) {
                                product.setPackageDescription(cell.getStringCellValue());

                            }
                            break;
                        case NUMERIC:
                            if(cell.getColumnIndex() == UPC_COLUMN_INDEX) {
                                product.setUpc(NumberToTextConverter.toText(cell.getNumericCellValue()));
                                dataRow = true;
                            }
                            else if (cell.getColumnIndex() == NUMBER_COLUMN_INDEX) {
                                product.setNumber((int) cell.getNumericCellValue());
                            }
                            else if (cell.getColumnIndex() == PACKAGE_QUANTITY_COLUMN_INDEX) {
                                product.setPackageQuantity((int) cell.getNumericCellValue());
                            }
                            else if (cell.getColumnIndex() == QUANTITY_SOLD_COLUMN_INDEX) {
                                product.setQuantitySold((int) cell.getNumericCellValue());
                            }
                            else if (cell.getColumnIndex() == UNIT_RETAIL_COLUMN_INDEX) {
                                product.setUnitRetail(cell.getNumericCellValue());
                            }
                            else if (cell.getColumnIndex() == EXTENDED_RETAIL_COLUMN_INDEX) {
                                product.setExtendedRetail(cell.getNumericCellValue());
                            }
                            break;

                    }
                    if(skipRow) break;
                }
                if(skipRow){
                    skipRow = false;
                    continue;
                }
                if(product.getUpc() != null)
                    products.add(product);

            }
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}