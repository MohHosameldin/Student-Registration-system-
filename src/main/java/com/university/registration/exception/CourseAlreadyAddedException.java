package com.university.registration.exception;

public class CourseAlreadyAddedException extends RuntimeException {
    public CourseAlreadyAddedException(String message) {
        super(message);
    }
}