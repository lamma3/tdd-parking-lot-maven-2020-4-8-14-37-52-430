package com.oocl;

import com.oocl.exception.ParkingLotFullException;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        try {
            parkingLot.park(car);
            return new ParkingTicket(car);
        } catch (ParkingLotFullException e) {
            return null;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            return null;
        }
        Car car = parkingTicket.getCar();
        if (!parkingLot.contains(car)) {
            return null;
        }
        parkingLot.take(car);
        return car;
    }
}
