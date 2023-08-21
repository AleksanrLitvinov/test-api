package ru.maxima.rest_api.testapi.exception;

public class PersonNotSuccessCreatedException extends RuntimeException {
    public PersonNotSuccessCreatedException(String message) {
        super(message);
    }
}
