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
        return lotteryRepo.findById(parsedDate)
                .orElse(getLotteryLogHelper(parsedDate));

    }

    private LotteryTrackerLog getLotteryLogHelper(LocalDate parsedDate) {
        LotteryTrackerLog log =  lotteryRepo.findById(parsedDate.minusDays(1)).orElse(null);
        LotteryTrackerLog todayLog = new LotteryTrackerLog(parsedDate);
        if (log != null && log.isLogComplete()) {
            todayLog.transferEODData(log);
        }
        return todayLog;
    }
}
