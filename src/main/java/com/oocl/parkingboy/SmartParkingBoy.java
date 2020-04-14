package com.oocl.parkingboy;

import com.oocl.ParkingLot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    protected ParkingLot findGoodParkingLot() {
        // return first parking lot witch is not full and with the largest number of empty space
        return this.parkingLotList.stream()
                .filter(parkingLot -> calculateEmptyPosition(parkingLot) > 0)
                .max(Comparator.comparing(this::calculateEmptyPosition))
                .orElse(null);
    }
}
