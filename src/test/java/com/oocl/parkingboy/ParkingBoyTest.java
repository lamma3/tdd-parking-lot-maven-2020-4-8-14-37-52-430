package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import com.oocl.ParkingTicket;
import com.oocl.exception.MissingParkingTicketException;
import com.oocl.exception.ParkingLotFullException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
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
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot1.getCapacity()).thenReturn(1);
        Mockito.when(parkingLot1.getOccupied()).thenReturn(1);

        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot2.getCapacity()).thenReturn(1);
        Mockito.when(parkingLot2.getOccupied()).thenReturn(0);

        ParkingLot parkingLot3 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot3.getCapacity()).thenReturn(10);
        Mockito.when(parkingLot3.getOccupied()).thenReturn(0);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2, parkingLot3);
        Car car = new Car();
        parkingBoy.park(car);

        Mockito.verify(parkingLot1, Mockito.never()).park(car);
        Mockito.verify(parkingLot2).park(car);
        Mockito.verify(parkingLot3, Mockito.never()).park(car);
    }
}
