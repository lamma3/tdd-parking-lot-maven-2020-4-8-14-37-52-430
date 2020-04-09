package com.oocl.exception;

public class MissingPackingTicketException extends RuntimeException {

    private static final String MESSAGE = "Please provide your parking ticket.";

    public MissingPackingTicketException() {
        super(MESSAGE);
    }
}
