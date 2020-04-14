package com.oocl.parkingboy;

import com.oocl.ParkingLot;
import com.oocl.parkingboy.findParkingLotStrategy.LargestAvailableStrategy;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
        this.findParkingLotStrategy = new LargestAvailableStrategy();
    }
}
