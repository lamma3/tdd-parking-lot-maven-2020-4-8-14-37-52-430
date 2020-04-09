package com.oocl;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        parkingLot.park(car);
        return new ParkingTicket(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            return null;
        }
        Car car = parkingTicket.getCar();
        parkingLot.take(car);
        return car;
    }
}
