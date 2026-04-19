package com.aiperson.service.application.result;

public sealed interface Result<T> permits Result.Success, Result.Failure {

    record Success<T>(T data) implements Result<T> {
    }

    record Failure<T>(String error, String details) implements Result<T> {
    }
}