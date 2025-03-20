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
public class LotteryTracker {

    @Id
    private LocalDate date;

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

    private boolean logComplete;


}
