package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingBoyTest {

    private static final int PARKING_LOT_CAPACITY = 10;

    ParkingBoy parkingBoy;

    @Before
    public void setUp() {
        parkingBoy = new ParkingBoy(new ParkingLot());
    }

    @Test
    public void should_return_ticket_when_park_car() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assert.assertEquals(car, parkingTicket.getCar());
    }

    @Test
    public void should_return_car_when_given_ticket() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car result = parkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, result);
    }

    @Test
    public void should_return_null_when_no_ticket() {
        Car result = parkingBoy.fetch(null);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_ticket_is_wrong() {
        Car car = new Car();
        ParkingTicket parkingTicket = new ParkingTicket(car);
        Car result = parkingBoy.fetch(parkingTicket);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_ticket_used() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        Car result = parkingBoy.fetch(parkingTicket);
        Assert.assertNull(result);
    }

    @Test
    public void should_return_null_when_car_park_full() {
        for (int i = 0; i < PARKING_LOT_CAPACITY; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_return_null_when_car_already_parked() {
        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assert.assertNull(parkingTicket);
    }
}
