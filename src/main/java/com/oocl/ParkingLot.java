package com.oocl;

import com.oocl.exception.CarAlreadyParkedException;
import com.oocl.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static final int CAPACITY = 10;
    List<Car> carList = new ArrayList<>();

    public ParkingLot() {

    }

    public ParkingLot(int capacity) {

    }

    public void park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        if (contains(car)) {
            throw new CarAlreadyParkedException();
        }
        carList.add(car);
    }

    private boolean isFull() {
        return carList.size() >= CAPACITY;
    }

    public void take(Car car) {
        carList.remove(car);
    }

    public boolean contains(Car car) {
        return carList.contains(car);
    }
}
