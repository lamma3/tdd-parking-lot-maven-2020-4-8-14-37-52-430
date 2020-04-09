package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.MissingParkingTicketException;
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

    @Test
    public void should_not_able_to_specify_parking_boy_to_park_when_parking_boy_not_in_list() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = manager.park(car, parkingBoy);
        Assertions.assertNull(parkingTicket);
    }

    @Test
    public void should_able_to_specify_parking_boy_to_fetch() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.add(parkingBoy);

        Car car = new Car();
        ParkingTicket parkingTicket = manager.park(car, parkingBoy);
        Car result = manager.fetch(parkingTicket, parkingBoy);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void should_not_able_to_specify_parking_boy_to_fetch_when_parking_boy_not_in_list() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = manager.park(car, parkingBoy);
        Assertions.assertThrows(MissingParkingTicketException.class,
                () -> manager.fetch(parkingTicket, parkingBoy));
    }
}