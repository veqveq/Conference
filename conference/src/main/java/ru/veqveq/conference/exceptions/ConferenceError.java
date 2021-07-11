package ru.veqveq.conference.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ConferenceError {
    private int status;
    private String message;
    private Date timestamp;

    public ConferenceError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
