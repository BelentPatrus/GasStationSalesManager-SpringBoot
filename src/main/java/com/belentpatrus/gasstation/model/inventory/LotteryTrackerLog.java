package com.belentpatrus.gasstation.model.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LotteryTrackerLog {

    @Id
    private LocalDate date;
    private boolean logComplete;

    // Morning Count
    private int morningCount2;   // $2 tickets count in the morning
    private int morningCount3;   // $3 tickets count in the morning
    private int morningCount5;   // $5 tickets count in the morning
    private int morningCount10;  // $10 tickets count in the morning
    private int morningCount20;  // $20 tickets count in the morning
    private int morningCount30;  // $30 tickets count in the morning
    private int morningCount50;  // $50 tickets count in the morning
    private int morningCount100; // $100 tickets count in the morning

    // End-of-Day Updates
    private int packsOpened2;   // New packs of $2 tickets opened during the day
    private int packsOpened3;
    private int packsOpened5;
    private int packsOpened10;
    private int packsOpened20;
    private int packsOpened30;
    private int packsOpened50;
    private int packsOpened100;

    public LotteryTrackerLog(LocalDate date) {
        this.date = date;
        this.logComplete = false;
    }

}
