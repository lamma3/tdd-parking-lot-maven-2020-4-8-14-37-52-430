package com.oocl.exception;

public class UnrecognizedParkingTicketException extends RuntimeException {

    private static final String MESSAGE = "Unrecognized parking ticket.";

    public UnrecognizedParkingTicketException() {
        super(MESSAGE);
    }
}
