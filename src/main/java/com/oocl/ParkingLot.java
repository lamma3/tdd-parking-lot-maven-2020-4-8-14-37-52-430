package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private Integer capacity = 10;
    List<Car> carList = new ArrayList<>();

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(Car car) {
        carList.add(car);
    }

    public void take(Car car) {
        carList.remove(car);
    }

    public boolean contains(Car car) {
        return carList.contains(car);
    }

    public int getEmptyPosition() {
        return capacity - carList.size();
    }

    public double getAvailableRate() {
        return (double) getEmptyPosition() / capacity;
    }
}
