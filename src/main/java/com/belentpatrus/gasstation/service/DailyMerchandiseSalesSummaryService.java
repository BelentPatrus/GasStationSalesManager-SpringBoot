package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.repository.DailyMerchandiseSalesRepository;
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



}
