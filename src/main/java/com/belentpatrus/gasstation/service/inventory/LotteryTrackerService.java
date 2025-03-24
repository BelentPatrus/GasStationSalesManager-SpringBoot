package com.belentpatrus.gasstation.service.inventory;

import com.belentpatrus.gasstation.model.inventory.LotteryTrackerLog;
import com.belentpatrus.gasstation.repository.inventory.LotteryTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LotteryTrackerService {

    LotteryTrackerRepository lotteryRepo;

    @Autowired
    public LotteryTrackerService(LotteryTrackerRepository lotteryRepo) {
        this.lotteryRepo = lotteryRepo;
    }

    public LotteryTrackerLog getLotteryLog(LocalDate parsedDate) {
        return lotteryRepo.findById(parsedDate).orElse(new LotteryTrackerLog(parsedDate));
    }
}
