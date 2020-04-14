package com.oocl.parkingboy;

import com.oocl.ParkingLot;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    protected ParkingLot findGoodParkingLot() {
        // return first parking lot witch is not full and with the highest available rate
        return this.parkingLotList.stream()
                .filter(parkingLot -> calculateEmptyPosition(parkingLot) > 0)
                .max(Comparator.comparing(this::calculateAvailableRate))
                .orElse(null);
    }

    private double calculateAvailableRate(ParkingLot parkingLot) {
        return (double) calculateEmptyPosition(parkingLot) / parkingLot.getCapacity();
    }
}
