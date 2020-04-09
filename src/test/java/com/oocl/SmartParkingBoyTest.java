package com.oocl;

import com.oocl.parkingboy.ParkingBoy;
import com.oocl.parkingboy.SmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmartParkingBoyTest {

    @Test
    public void should_park_to_first_parking_lot_with_more_position() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);
        Car car = new Car();
        parkingBoy.park(car);

        Assertions.assertFalse(parkingLot1.contains(car));
        Assertions.assertTrue(parkingLot2.contains(car));
        Assertions.assertFalse(parkingLot3.contains(car));
    }
}
