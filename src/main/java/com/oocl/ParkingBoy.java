package com.oocl;

import com.oocl.exception.CarAlreadyParkedException;
import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.UnrecognizedParkingTicketException;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot... parkingLotArray) {
        this.parkingLot = parkingLotArray[0];
    }

    public ParkingTicket park(Car car) {
        try {
            parkingLot.park(car);
            return new ParkingTicket(car);
        } catch (CarAlreadyParkedException e) {
            return null;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new MissingParkingTicketException();
        }
        Car car = parkingTicket.getCar();
        if (!parkingLot.contains(car)) {
            throw new UnrecognizedParkingTicketException();
        }
        parkingLot.take(car);
        return car;
    }
}
