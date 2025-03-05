package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.repository.DailyMerchandiseSalesRepository;
import com.belentpatrus.gasstation.repository.ProductRepository;
import com.belentpatrus.gasstation.service.dto.SyncDailyMerchandiseSalesAndProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyncService {
    private final ProductRepository productRepository;
    private final DailyMerchandiseSalesRepository dailyMerchandiseSalesRepository;

    @Autowired
    public SyncService(ProductRepository productRepository, DailyMerchandiseSalesRepository dailyMerchandiseSalesRepository) {
        this.productRepository = productRepository;
        this.dailyMerchandiseSalesRepository = dailyMerchandiseSalesRepository;
    }

    public SyncDailyMerchandiseSalesAndProductDTO notSyncedMerchandiseItemSales(DailyMerchandiseSales dailyMerchandiseSales) {
        List<MerchandiseItemSale> notSyncedMerchandiseItemSales = new ArrayList<>();
        for(MerchandiseItemSale merchandiseItemSale : dailyMerchandiseSales.getMerchandiseItemSales()) {
            if(productRepository.findById(merchandiseItemSale.getUpc()).isEmpty()) {
                notSyncedMerchandiseItemSales.add(merchandiseItemSale);
            };
        }
        return new SyncDailyMerchandiseSalesAndProductDTO(notSyncedMerchandiseItemSales);
    }
}
