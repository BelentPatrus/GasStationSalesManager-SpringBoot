package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.enums.Department;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.model.dailysales.enums.ProductCategory;
import com.belentpatrus.gasstation.repository.DailyMerchandiseSalesRepository;
import com.belentpatrus.gasstation.service.dto.DailyMerchandiseSalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyMerchandiseSalesSummaryService {

    private final DailyMerchandiseSalesRepository repo;

    @Autowired
    public DailyMerchandiseSalesSummaryService(DailyMerchandiseSalesRepository repo) {
        this.repo = repo;
    }

    public DailyMerchandiseSales getDailyMerchandiseSales(LocalDate date){
        return repo.findByDate(date).getFirst();
    }

    public double getTotalSoldByDepartment(Long id, Department department){
        DailyMerchandiseSales dailyMerchandiseSales = repo.findById(id).get();
        return dailyMerchandiseSales.getMerchandiseItemSales().stream().filter(mis -> mis.getDepartment().equals(department)).mapToDouble(MerchandiseItemSale::getExtendedRetail).sum();
    }

    public double getTotalSoldByProductCategory(Long id, ProductCategory productCategory){
        DailyMerchandiseSales dailyMerchandiseSales = repo.findById(id).get();
        return dailyMerchandiseSales.getMerchandiseItemSales().stream().filter(mis -> mis.getProductCategory().equals(productCategory)).mapToDouble(MerchandiseItemSale::getExtendedRetail).sum();
    }


    public DailyMerchandiseSalesSummaryDTO getDailyMerchandiseSalesSummary(long id) {
        DailyMerchandiseSales dailyMerchandiseSales = repo.findById(id).get();
        DailyMerchandiseSalesSummaryDTO dto = new DailyMerchandiseSalesSummaryDTO(dailyMerchandiseSales.getDate(), dailyMerchandiseSales.getTotalExtendedRetail(), dailyMerchandiseSales.getTotalQuantitySold());
        for(Department department : dailyMerchandiseSales.getDepartmentSales()) {
            dto.getDepartmentSales().put(department, getTotalSoldByDepartment(id, department));
        }

        for(ProductCategory productCategory : dailyMerchandiseSales.getProductCategory()) {
            dto.getProductCategorySales().put(productCategory, getTotalSoldByProductCategory(id, productCategory));
        }

        return dto;
    }
}
