package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Integer capacity = 10;
    Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car take(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

    public boolean contains(Car car) {
        return parkingTicketCarMap.containsValue(car);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return parkingTicketCarMap.size();
    }
}
