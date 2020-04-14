package com.oocl.parkingboy;

import com.oocl.ParkingLot;
import com.oocl.parkingboy.findParkingLotStrategy.LargestAvailableRateStrategy;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
        this.findParkingLotStrategy = new LargestAvailableRateStrategy();
    }
}
