package com.oocl.exception;

public class ParkingLotFullException extends RuntimeException {

    private static final String MESSAGE = "Not enough position.";

    public ParkingLotFullException() {
        super(MESSAGE);
    }
}
