package com.belentpatrus.gasstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalSales {
    @Id
    private Long id;

    @OneToMany
    private List<DailyMerchandiseSales> dailyMerchandiseSales;
}
