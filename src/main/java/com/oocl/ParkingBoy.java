package com.oocl;

import com.oocl.exception.CarAlreadyParkedException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        try {
            parkingLot.park(car);
            return new ParkingTicket(car);
        } catch (ParkingLotFullException | CarAlreadyParkedException e) {
            return null;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            return null;
        }
        Car car = parkingTicket.getCar();
        if (!parkingLot.contains(car)) {
            throw new UnrecognizedParkingTicketException();
        }
        parkingLot.take(car);
        return car;
    }
}
