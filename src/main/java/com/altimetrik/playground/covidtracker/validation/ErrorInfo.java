package com.altimetrik.playground.covidtracker.validation;

public class ErrorInfo {
    public final String message;

    public ErrorInfo(Exception exception) {
        this.message = exception.getMessage();
    }
}
