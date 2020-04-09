package com.oocl;

import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot... parkingLotArray) {
        this.parkingLotList = Arrays.asList(parkingLotArray);
    }

    public ParkingTicket park(Car car) {
        if (findParkingLotOf(car) != null) {
            return null;
        }
        ParkingLot parkingLot = findFirstAvailableParkingLot();
        if (parkingLot == null) {
            throw new ParkingLotFullException();
        }
        parkingLot.park(car);
        return new ParkingTicket(car);
    }

    private ParkingLot findParkingLotOf(Car car) {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.contains(car))
                .findAny()
                .orElse(null);
    }

    private ParkingLot findFirstAvailableParkingLot() {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElse(null);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new MissingParkingTicketException();
        }
        Car car = parkingTicket.getCar();
        ParkingLot parkingLot = findParkingLotOf(car);
        if (parkingLot == null) {
            throw new UnrecognizedParkingTicketException();
        }
        parkingLot.take(car);
        return car;
    }
}
