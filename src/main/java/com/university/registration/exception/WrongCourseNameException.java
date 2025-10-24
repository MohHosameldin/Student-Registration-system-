package com.university.registration.exception;

public class WrongCourseNameException extends RuntimeException {
    public WrongCourseNameException(String message) {
        super(message);
    }
}
