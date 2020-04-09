package com.oocl;

import com.oocl.parkingboy.ParkingBoy;
import com.oocl.parkingboy.SuperSmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_park_to_first_parking_lot_with_higher_available_rate() {
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        parkingBoy.park(car1);
        Car car2 = new Car();
        parkingBoy.park(car2);

        Assertions.assertTrue(parkingLot1.contains(car1));
        Assertions.assertFalse(parkingLot2.contains(car2));
    }
}
