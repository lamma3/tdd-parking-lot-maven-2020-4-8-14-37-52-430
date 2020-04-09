package com.oocl;

public class ParkingTicket {

    private final Car car;

    public ParkingTicket(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return this.car;
    }
}
