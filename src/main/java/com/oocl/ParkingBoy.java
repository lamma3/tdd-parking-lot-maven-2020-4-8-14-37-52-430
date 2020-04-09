package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private List<ParkingTicket> parkingTicketList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        parkingLot.park(car);
        ParkingTicket parkingTicket = new ParkingTicket(car);
        parkingTicketList.add(parkingTicket);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }
}
