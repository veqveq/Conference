package ru.veqveq.conference.exceptions;

public class IncorrectOwnerException extends RuntimeException{
    public IncorrectOwnerException(String message) {
        super(message);
    }
}
