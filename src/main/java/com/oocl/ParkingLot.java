package com.oocl;

import com.oocl.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static final int CAPACITY = 10;
    List<Car> carList = new ArrayList<>();

    public void park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
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
