package ru.otus.exeptions;

public class ImpossibleExtraditeException extends RuntimeException {

    public ImpossibleExtraditeException(String message) {
        super(message);
    }
}
