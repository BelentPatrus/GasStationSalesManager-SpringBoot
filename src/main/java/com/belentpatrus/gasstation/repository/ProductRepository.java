package com.belentpatrus.gasstation.repository;

import com.belentpatrus.gasstation.model.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
