package com.oocl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmartParkingBoyTest {

    @Test
    public void should_park_to_parking_lot_with_more_position() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();
        parkingBoy.park(car);

        Assertions.assertFalse(parkingLot1.contains(car));
        Assertions.assertTrue(parkingLot2.contains(car));
    }
}
