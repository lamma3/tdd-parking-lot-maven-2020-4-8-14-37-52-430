package com.oocl.parkingboy;

import com.oocl.ParkingLot;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    ParkingLot findAvailableParkingLot() {
        // return first parking lot witch is not full and with the highest available rate
        return this.getParkingLotList().stream()
                .filter(parkingLot -> parkingLot.getAvailableRate() > 0)
                .max(Comparator.comparing(ParkingLot::getAvailableRate))
                .orElse(null);
    }
}
