package com.oocl.parkingboy.findParkingLotStrategy;

import com.oocl.ParkingLot;

import java.util.List;

public class FirstAvailableStrategy extends FindParkingLotStrategy {

    public ParkingLot find(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(this::isAvailable)
                .findFirst()
                .orElse(null);
    }
}
