package com.oocl.parkingboy.findParkingLotStrategy;

import com.oocl.ParkingLot;

import java.util.List;

public abstract class FindParkingLotStrategy {
    public abstract ParkingLot find(List<ParkingLot> parkingLotList);

    protected boolean isAvailable(ParkingLot parkingLot) {
        return calculateEmptyPosition(parkingLot) > 0;
    }

    protected int calculateEmptyPosition(ParkingLot parkingLot) {
        return parkingLot.getCapacity() - parkingLot.getOccupied();
    }
}
