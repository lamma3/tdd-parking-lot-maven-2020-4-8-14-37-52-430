package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Car result = manager.fetch(parkingTicket, parkingBoy);
        Assertions.assertNull(result);
    }

    @Test
    public void should_able_to_park_and_fetch() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = manager.park(car);
        Car result = manager.fetch(parkingTicket);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void should_not_able_to_fetch_when_parking_lot_is_not_managed() {
        Manager manager = new Manager(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> manager.fetch(parkingTicket));
    }

    @Test
    public void should_throw_exception_when_parking_boy_park_throw_exception() {
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        Mockito.doThrow(new ParkingLotFullException())
                .when(parkingBoy).park(Mockito.any());

        Manager manager = new Manager(new ParkingLot());
        manager.add(parkingBoy);

        ParkingLotFullException exception = Assertions.assertThrows(ParkingLotFullException.class,
                () -> manager.park(new Car(), parkingBoy));
        Assertions.assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_parking_boy_fetch_throw_exception() {
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        Mockito.doThrow(new UnrecognizedParkingTicketException())
                .when(parkingBoy).park(Mockito.any());

        Manager manager = new Manager(new ParkingLot());
        manager.add(parkingBoy);

        UnrecognizedParkingTicketException exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> manager.park(new Car(), parkingBoy));
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
