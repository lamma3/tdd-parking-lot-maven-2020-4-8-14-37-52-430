package com.oocl;

import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot... parkingLotArray) {
        this.parkingLot = parkingLotArray[0];
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.contains(car)) {
            return null;
        }
        if (parkingLot.isFull()) {
            throw new ParkingLotFullException();
        }
        parkingLot.park(car);
        return new ParkingTicket(car);
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
