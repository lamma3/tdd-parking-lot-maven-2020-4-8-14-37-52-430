package com.oocl;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    List<Car> carList = new ArrayList<>();

    public void park(Car car) {
        carList.add(car);
    }
}
