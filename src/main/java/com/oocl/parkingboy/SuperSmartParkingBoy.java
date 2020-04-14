package com.oocl.parkingboy;

import com.oocl.ParkingLot;
import com.oocl.parkingboy.findParkingLotStrategy.FindParkingLotStrategy;
import com.oocl.parkingboy.findParkingLotStrategy.LargestAvailableRateStrategy;

public class SuperSmartParkingBoy extends ParkingBoy {

    private final static FindParkingLotStrategy FIND_PARKING_LOT_STRATEGY = new LargestAvailableRateStrategy();

    public SuperSmartParkingBoy(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    @Override
    protected FindParkingLotStrategy getFindParkingLotStrategy() {
        return FIND_PARKING_LOT_STRATEGY;
    }
}
