package com.belentpatrus.gasstation.model.dailysales;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyMerchandiseSales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @OneToMany
    private List<MerchandiseItemSale> merchandiseItemSales;

    public DailyMerchandiseSales(LocalDate date, List<MerchandiseItemSale> merchandiseItemSales) {
        this.date = date;
        this.merchandiseItemSales = merchandiseItemSales;
    }
}
