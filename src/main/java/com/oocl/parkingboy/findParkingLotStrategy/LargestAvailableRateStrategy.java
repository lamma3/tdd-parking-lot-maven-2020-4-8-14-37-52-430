package com.oocl.parkingboy.findParkingLotStrategy;

import com.oocl.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class LargestAvailableRateStrategy extends FindParkingLotStrategy {

    public ParkingLot find(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(this::isAvailable)
                .max(Comparator.comparing(this::calculateAvailableRate))
                .orElse(null);
    }

    private double calculateAvailableRate(ParkingLot parkingLot) {
        return (double) calculateEmptyPosition(parkingLot) / parkingLot.getCapacity();
    }
}
