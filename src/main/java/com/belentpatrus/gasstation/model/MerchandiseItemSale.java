package com.belentpatrus.gasstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchandiseItemSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchandiseItemId;

    private String upc;
    private int number;
    private String description;
    private String packageDescription;
    private int packageQuantity;
    private int quantitySold;
    private double unitRetail;
    private double extendedRetail;

    public MerchandiseItemSale(String upc, int number, String description, String packageDescription, int packageQuantity, int quantitySold, double unitRetail, double extendedRetail) {
        this.upc = upc;
        this.number = number;
        this.description = description;
        this.packageDescription = packageDescription;
        this.packageQuantity = packageQuantity;
        this.quantitySold = quantitySold;
        this.unitRetail = unitRetail;
        this.extendedRetail = extendedRetail;
    }

}
