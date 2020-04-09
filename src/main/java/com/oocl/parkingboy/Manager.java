package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;

import java.util.ArrayList;
import java.util.List;

public class Manager extends ParkingBoy {

    private List<ParkingBoy> managementList = new ArrayList<>();

    public Manager(ParkingLot... parkingLotArray) {
        super(parkingLotArray);
    }

    public void add(ParkingBoy parkingBoy) {
        this.managementList.add(parkingBoy);
    }

    public ParkingTicket park(Car car, ParkingBoy parkingBoy) {
        if (!managementList.contains(parkingBoy)) {
            return null;
        }
        return parkingBoy.park(car);
    }
}
