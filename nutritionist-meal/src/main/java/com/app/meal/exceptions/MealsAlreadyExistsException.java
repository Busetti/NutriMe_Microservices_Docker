package com.app.meal.exceptions;

public class MealsAlreadyExistsException extends Throwable {

    public MealsAlreadyExistsException(String message) {
        super(message);
    }
}
