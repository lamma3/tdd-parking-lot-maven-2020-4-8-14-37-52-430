package com.oocl;

import com.oocl.exception.MissingPackingTicketException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {

    private static final int PARKING_LOT_CAPACITY = 10;

    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUp() {
        parkingBoy = new ParkingBoy(new ParkingLot());
    }

    @Test
    public void should_return_ticket_when_park_car() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assertions.assertEquals(car, parkingTicket.getCar());
    }

    @Test
    public void should_return_car_when_given_ticket() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car result = parkingBoy.fetch(parkingTicket);
        Assertions.assertEquals(car, result);
    }

    @Test
    public void should_throw_exception_when_no_ticket() {
        MissingPackingTicketException exception = Assertions.assertThrows(MissingPackingTicketException.class,
                () -> parkingBoy.fetch(null));
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_ticket_is_wrong() {
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket(car);
        UnrecognizedParkingTicketException exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(parkingTicket));
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_ticket_used() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        UnrecognizedParkingTicketException exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(parkingTicket));
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_null_when_car_park_full() {
        for (int i = 0; i < PARKING_LOT_CAPACITY; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assertions.assertNull(parkingTicket);
    }

    @Test
    public void should_return_null_when_car_already_parked() {
        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assertions.assertNull(parkingTicket);
    }
}
