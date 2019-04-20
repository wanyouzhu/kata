package com.example.kata.args;

class ArgsException extends RuntimeException {
    ArgsException(String message) {
        super(message);
    }

    ArgsException(String message, Throwable cause) {
        super(message, cause);
    }
}
