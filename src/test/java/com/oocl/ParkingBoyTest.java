package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingBoyTest {

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
}
