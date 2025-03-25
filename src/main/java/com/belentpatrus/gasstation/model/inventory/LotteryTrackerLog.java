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
    private boolean morningLogComplete;

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

    private int ticketsSold2;   // Number of $2 tickets sold during the day
    private int ticketsSold3;
    private int ticketsSold5;
    private int ticketsSold10;
    private int ticketsSold20;
    private int ticketsSold30;
    private int ticketsSold50;
    private int ticketsSold100;

    // Final Count (Calculated at end of the day)
    private int calculatedFinalCount2;
    private int calculatedFinalCount3;
    private int calculatedFinalCount5;
    private int calculatedFinalCount10;
    private int calculatedFinalCount20;
    private int calculatedFinalCount30;
    private int calculatedFinalCount50;
    private int calculatedFinalCount100;


    // EOD Count
    private int eodCount2;   // $2 tickets count in the morning
    private int eodCount3;   // $3 tickets count in the morning
    private int eodCount5;   // $5 tickets count in the morning
    private int eodCount10;  // $10 tickets count in the morning
    private int eodCount20;  // $20 tickets count in the morning
    private int eodCount30;  // $30 tickets count in the morning
    private int eodCount50;  // $50 tickets count in the morning
    private int eodCount100; // $100 tickets count in the morning


    public LotteryTrackerLog(LocalDate date) {
        this.date = date;
        this.logComplete = false;
        this.morningLogComplete = false;
    }


    public void transferEODData(LotteryTrackerLog log) {
        this.morningCount2 = log.getEodCount2();
        this.morningCount3 = log.getEodCount3();
        this.morningCount5 = log.getEodCount5();
        this.morningCount10 = log.getEodCount10();
        this.morningCount20 = log.getEodCount20();
        this.morningCount30 = log.getEodCount30();
        this.morningCount50 = log.getEodCount50();
        this.morningCount100 = log.getEodCount100();
        morningLogComplete = true;

    }
}
