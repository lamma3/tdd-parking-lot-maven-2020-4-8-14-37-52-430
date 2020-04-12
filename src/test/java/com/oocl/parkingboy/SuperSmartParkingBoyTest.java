package com.oocl.parkingboy;

import com.oocl.Car;
import com.oocl.ParkingLot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_park_to_first_parking_lot_with_higher_available_rate() {
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot1.getCapacity()).thenReturn(10);
        Mockito.when(parkingLot1.getOccupied()).thenReturn(3);

        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot2.getCapacity()).thenReturn(5);
        Mockito.when(parkingLot2.getOccupied()).thenReturn(1);

        ParkingLot parkingLot3 = Mockito.mock(ParkingLot.class);
        Mockito.when(parkingLot3.getCapacity()).thenReturn(1);
        Mockito.when(parkingLot3.getOccupied()).thenReturn(0);

        ParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);
        Car car = new Car();
        parkingBoy.park(car);

        Mockito.verify(parkingLot1, Mockito.never()).park(car);
        Mockito.verify(parkingLot2, Mockito.never()).park(car);
        Mockito.verify(parkingLot3).park(car);
    }
}
