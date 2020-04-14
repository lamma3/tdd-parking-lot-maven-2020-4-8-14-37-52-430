package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot... parkingLotArray) {
        this.parkingLotList = Arrays.asList(parkingLotArray);
    }

    public ParkingTicket park(Car car) {
        if (findParkingLotOf(car) != null) {
            return null;
        }
        ParkingLot parkingLot = findGoodParkingLot();
        if (parkingLot == null) {
            throw new ParkingLotFullException();
        }
        return parkingLot.park(car);
    }

    private ParkingLot findParkingLotOf(Car car) {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.contains(car))
                .findAny()
                .orElse(null);
    }

    protected ParkingLot findGoodParkingLot() {
        // return first parking lot which is not full
        return parkingLotList.stream()
                .filter(parkingLot -> calculateEmptyPosition(parkingLot) > 0)
                .findFirst()
                .orElse(null);
    }

    int calculateEmptyPosition(ParkingLot parkingLot) {
        return parkingLot.getCapacity() - parkingLot.getOccupied();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new MissingParkingTicketException();
        }

        Car car;
        for (ParkingLot parkingLot: parkingLotList) {
            car = parkingLot.take(parkingTicket);
            if (car != null) {
                return car;
            }
        }

        throw new UnrecognizedParkingTicketException();
    }
}
