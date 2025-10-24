package com.university.registration.exception;

public class WrongCourseMajorException extends RuntimeException {
    public WrongCourseMajorException(String message) {
        super(message);
    }
}