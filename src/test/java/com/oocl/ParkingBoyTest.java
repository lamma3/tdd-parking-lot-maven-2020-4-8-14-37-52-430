package com.oocl;

import com.oocl.exception.CarAlreadyParkedException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ParkingBoyTest {

    private static final int PARKING_LOT_CAPACITY = 10;

    @Test
    public void should_return_ticket_when_park_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = packingBoy.park(car);
        Assert.assertEquals(car, parkingTicket.getCar());
    }

    @Test
    public void should_return_car_when_given_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = packingBoy.park(car);
        Car result = packingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, result);
    }

    @Test
    public void should_return_null_when_no_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car result = packingBoy.fetch(null);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket(car);
        Car result = packingBoy.fetch(parkingTicket);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_ticket_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = packingBoy.park(car);
        packingBoy.fetch(parkingTicket);
        Car result = packingBoy.fetch(parkingTicket);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_car_park_full() {
        ParkingBoy packingBoy = new ParkingBoy(new ParkingLot());
        for (int i = 0; i < PARKING_LOT_CAPACITY; i++) {
            Car car = new Car();
            packingBoy.park(car);
        }
        Car car = new Car();
        ParkingTicket parkingTicket = packingBoy.park(car);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_return_null_when_car_already_parked() {
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        Mockito.doThrow(new CarAlreadyParkedException())
                .when(parkingLot).park(Mockito.any());

        ParkingBoy packingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = packingBoy.park(car);
        Assert.assertNull(parkingTicket);
    }
}
