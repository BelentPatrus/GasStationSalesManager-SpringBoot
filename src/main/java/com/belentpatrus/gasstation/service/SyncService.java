package com.belentpatrus.gasstation.service;

import com.belentpatrus.gasstation.model.dailysales.DailyMerchandiseSales;
import com.belentpatrus.gasstation.model.dailysales.MerchandiseItemSale;
import com.belentpatrus.gasstation.model.inventory.Product;
import com.belentpatrus.gasstation.repository.DailyMerchandiseSalesRepository;
import com.belentpatrus.gasstation.repository.ProductRepository;
import com.belentpatrus.gasstation.service.dto.DailyMerchandiseSalesSummaryDTO;
import com.belentpatrus.gasstation.service.dto.MerchandiseItemSaleDTO;
import com.belentpatrus.gasstation.service.dto.SyncDailyMerchandiseSalesAndProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SyncService {
    private final ProductRepository productRepository;
    private final DailyMerchandiseSalesRepository dailyMerchandiseSalesRepository;

    @Autowired
    public SyncService(ProductRepository productRepository, DailyMerchandiseSalesRepository dailyMerchandiseSalesRepository) {
        this.productRepository = productRepository;
        this.dailyMerchandiseSalesRepository = dailyMerchandiseSalesRepository;
    }

    public SyncDailyMerchandiseSalesAndProductDTO notSyncedMerchandiseItemSales(DailyMerchandiseSalesSummaryDTO dailyMerchandiseSalesDTO) {
        List<String> upcs = dailyMerchandiseSalesDTO.getMerchandiseItemSales().stream()
                .map(MerchandiseItemSaleDTO::getUpc)
                .collect(Collectors.toList());

        Set<String> existingUpcs = new HashSet<>(productRepository.findAllById(upcs)
                .stream()
                .map(Product::getUpc)
                .collect(Collectors.toList()));

        List<MerchandiseItemSaleDTO> notSyncedMerchandiseItemSales = dailyMerchandiseSalesDTO.getMerchandiseItemSales().stream()
                .filter(sale -> !existingUpcs.contains(sale.getUpc()))
                .collect(Collectors.toList());

        return new SyncDailyMerchandiseSalesAndProductDTO(notSyncedMerchandiseItemSales);
    }
}
