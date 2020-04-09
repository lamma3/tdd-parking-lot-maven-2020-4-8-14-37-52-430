package com.oocl.exception;

public class MissingParkingTicketException extends RuntimeException {

    private static final String MESSAGE = "Please provide your parking ticket.";

    public MissingParkingTicketException() {
        super(MESSAGE);
    }
}
