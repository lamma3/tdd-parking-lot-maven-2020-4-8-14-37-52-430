package com.oocl.parkingboy;

import com.oocl.ParkingLot;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    ParkingLot findGoodParkingLot() {
        // return first parking lot witch is not full and with the highest available rate
        return this.getParkingLotList().stream()
                .filter(this::isAvailable)
                .max(Comparator.comparing(this::calculateAvailableRate))
                .orElse(null);
    }

    private double calculateAvailableRate(ParkingLot parkingLot) {
        return (double) parkingLot.getEmptyPosition() / parkingLot.getCapacity();
    }
}
