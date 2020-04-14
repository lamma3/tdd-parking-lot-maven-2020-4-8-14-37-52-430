package com.oocl.parkingboy.findParkingLotStrategy;

import com.oocl.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class LargestAvailableStrategy extends FindParkingLotStrategy {

    public ParkingLot find(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(this::isAvailable)
                .max(Comparator.comparing(this::calculateEmptyPosition))
                .orElse(null);
    }
}
