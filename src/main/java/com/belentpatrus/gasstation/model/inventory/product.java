package com.belentpatrus.gasstation.model.inventory;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class product {
    @Id
    private String upc;
    private String description;
    private String brand;
    private String category;
    private String baseCost;
}
