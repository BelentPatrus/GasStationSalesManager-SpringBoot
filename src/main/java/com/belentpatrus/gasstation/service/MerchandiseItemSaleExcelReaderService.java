package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.Department;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.model.dailysales.ProductCategory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;


@Service
public class MerchandiseItemSaleExcelReaderService {

    private static final int DEPARTMENT_COLUMN_INDEX_AND_DATE_INDEX = 0;
    private static final int PRODUCT_CATEGORY_COLUMN_INDEX = 2;
    private static final int UPC_COLUMN_INDEX = 4;
    private static final int NUMBER_COLUMN_INDEX = 5;
    private static final int DESCRIPTION_COLUMN_INDEX = 8;
    private static final int PACKAGE_DESCRIPTION_COLUMN_INDEX = 12;
    private static final int PACKAGE_QUANTITY_COLUMN_INDEX = 14;
    private static final int QUANTITY_SOLD_COLUMN_INDEX = 16;
    private static final int UNIT_RETAIL_COLUMN_INDEX = 17;
    private static final int EXTENDED_RETAIL_COLUMN_INDEX = 19;

    private static final Map<Integer, BiConsumer<MerchandiseItemSale,Cell>> stringHandlers = new HashMap<>();
    private static final Map<Integer, BiConsumer<MerchandiseItemSale,Cell>> numericHandlers = new HashMap<>();

    static {
        stringHandlers.put(DESCRIPTION_COLUMN_INDEX, (product, cell) -> product.setDescription(cell.getStringCellValue()));
        stringHandlers.put(PACKAGE_DESCRIPTION_COLUMN_INDEX, (product, cell) -> product.setPackageDescription(cell.getStringCellValue()));

        numericHandlers.put(UPC_COLUMN_INDEX, (product, cell) -> product.setUpc(NumberToTextConverter.toText(cell.getNumericCellValue())));
        numericHandlers.put(NUMBER_COLUMN_INDEX, (product, cell) -> product.setNumber((int) cell.getNumericCellValue()));
        numericHandlers.put(PACKAGE_QUANTITY_COLUMN_INDEX, (product, cell) -> product.setPackageQuantity((int) cell.getNumericCellValue()));
        numericHandlers.put(QUANTITY_SOLD_COLUMN_INDEX, (product, cell) -> product.setQuantitySold((int) cell.getNumericCellValue()));
        numericHandlers.put(UNIT_RETAIL_COLUMN_INDEX, (product, cell) -> product.setUnitRetail(cell.getNumericCellValue()));
        numericHandlers.put(EXTENDED_RETAIL_COLUMN_INDEX, (product, cell) -> product.setExtendedRetail(cell.getNumericCellValue()));
    }

    /**
     * Read merchandise item sales from an Excel file.
     *
     * @param filePath path to the Excel file
     * @return list of merchandise item sales
     */
    public DailyMerchandiseSales readProductsFromExcel(String filePath) {
        DailyMerchandiseSales dailyMerchandiseSales = new DailyMerchandiseSales();
        List<MerchandiseItemSale> products = new ArrayList<MerchandiseItemSale>();
        LocalDate date = null;
        boolean isData = false;
        Department department = null;
        ProductCategory productCategory = null;
        Pattern departmentPattern = Pattern.compile("Department:\\s*(\\d+)");
        Pattern productCategoryPattern = Pattern.compile("Product Category:\\s*(\\d+)");
        Pattern datePattern = Pattern.compile("Date:\\s*(\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher;
        AtomicBoolean skipRow = new AtomicBoolean(false);

        try(FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook wb = WorkbookFactory.create(fis);) {
            Sheet sheet = wb.getSheetAt(0); // Assuming data is in the first sheet

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                boolean dataRow = false;
                MerchandiseItemSale product = new MerchandiseItemSale();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            if(!dataRow){
                                if(cell.getColumnIndex() == DEPARTMENT_COLUMN_INDEX_AND_DATE_INDEX){
                                    matcher = departmentPattern.matcher(cell.getStringCellValue());
                                    if (matcher.find()) {
                                        department = Department.fromId(Integer.parseInt(matcher.group(1)));
                                    }
                                    matcher = datePattern.matcher(cell.getStringCellValue());
                                    if (matcher.find()) {
                                        String[] dateParts = matcher.group(1).split("/");
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                        date = LocalDate.parse(matcher.group(1), formatter);

                                    }
                                }else if(cell.getColumnIndex() == PRODUCT_CATEGORY_COLUMN_INDEX){
                                    matcher = productCategoryPattern.matcher(cell.getStringCellValue());
                                    if (matcher.find()) {
                                        productCategory = ProductCategory.fromId(Integer.parseInt(matcher.group(1)));
                                    }
                                }
                                skipRow.set(true);
                                break;
                            }
                            stringHandlers.getOrDefault(cell.getColumnIndex(), (p, c) -> {}).accept(product, cell);
                            break;
                        case NUMERIC:
                            if (cell.getColumnIndex() == UPC_COLUMN_INDEX) {
                                dataRow = true;
                            }
                            numericHandlers.get(cell.getColumnIndex()).accept(product, cell);
                            break;
                    }
                    if(skipRow.get()){
                        skipRow.set(false);
                        break;
                    }
                }
                if(product.getUpc() != null){
                    product.setDepartment(department);
                    product.setProductCategory(productCategory);
                    products.add(product);
                }
            }
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }
        return new DailyMerchandiseSales(date,products);
    }
}