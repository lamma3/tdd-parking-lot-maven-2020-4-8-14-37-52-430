package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {

    private static final int PARKING_LOT_CAPACITY = 10;

    @Test
    public void should_return_ticket_when_park_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_given_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car result = parkingBoy.fetch(parkingTicket);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void should_throw_exception_when_no_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        MissingParkingTicketException exception = Assertions.assertThrows(MissingParkingTicketException.class,
                () -> parkingBoy.fetch(null));
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_ticket_is_wrong() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = new ParkingTicket();
        UnrecognizedParkingTicketException exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(parkingTicket));
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_ticket_used() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        UnrecognizedParkingTicketException exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(parkingTicket));
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_car_park_full() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        for (int i = 0; i < PARKING_LOT_CAPACITY; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car car = new Car();
        ParkingLotFullException exception = Assertions.assertThrows(ParkingLotFullException.class,
                () -> parkingBoy.park(car));
        Assertions.assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_return_null_when_car_already_parked() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assertions.assertNull(parkingTicket);
    }

    @Test
    public void should_park_to_parking_lot_2_when_parking_lot_1_is_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        parkingBoy.park(car1);
        Car car2 = new Car();
        parkingBoy.park(car2);

        Assertions.assertFalse(parkingLot1.contains(car2));
        Assertions.assertTrue(parkingLot2.contains(car2));
    }
}
