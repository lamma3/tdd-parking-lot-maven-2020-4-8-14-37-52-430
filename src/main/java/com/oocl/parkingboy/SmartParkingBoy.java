package com.oocl.parkingboy;

import com.oocl.ParkingLot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    ParkingLot findAvailableParkingLot() {
        // return first parking lot witch is not full and with the largest number of empty space
        return this.getParkingLotList().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparing(ParkingLot::getEmptyPosition))
                .orElse(null);
    }
}
