package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import com.oocl.parkingboy.findParkingLotStrategy.FindParkingLotStrategy;
import com.oocl.parkingboy.findParkingLotStrategy.FirstAvailableStrategy;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

    private final static FindParkingLotStrategy FIND_PARKING_LOT_STRATEGY = new FirstAvailableStrategy();
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot... parkingLotArray) {
        this.parkingLotList = Arrays.asList(parkingLotArray);
    }

    protected FindParkingLotStrategy getFindParkingLotStrategy() {
        return FIND_PARKING_LOT_STRATEGY;
    }

    public ParkingTicket park(Car car) {
        if (findParkingLotOf(car) != null) {
            return null;
        }
        ParkingLot parkingLot = getFindParkingLotStrategy().find(parkingLotList);
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
