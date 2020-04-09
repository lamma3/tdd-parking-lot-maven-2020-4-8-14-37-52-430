package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    @Test
    public void should_able_to_specify_parking_boy_to_park() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.add(parkingBoy);

        Car car = new Car();
        ParkingTicket parkingTicket = manager.park(car, parkingBoy);
        Assertions.assertNotNull(parkingTicket);
    }
}
