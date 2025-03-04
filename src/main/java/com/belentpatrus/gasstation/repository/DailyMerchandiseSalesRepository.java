package com.belentpatrus.gasstation.repository;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyMerchandiseSalesRepository extends JpaRepository<DailyMerchandiseSales, Long> {


    List<DailyMerchandiseSales> findByDate(LocalDate date);
}
