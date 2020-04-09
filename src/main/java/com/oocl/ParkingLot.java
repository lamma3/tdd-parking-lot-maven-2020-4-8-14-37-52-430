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
        this.carList.add(car);
    }

    public boolean isFull() {
        return this.carList.size() >= this.capacity;
    }

    public void take(Car car) {
        this.carList.remove(car);
    }

    public boolean contains(Car car) {
        return this.carList.contains(car);
    }
}
