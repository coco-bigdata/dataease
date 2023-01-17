package io.tortoise.exception;

public class TortoiseException extends RuntimeException {

    private TortoiseException(String message) {
        super(message);
    }

    private TortoiseException(Throwable t) {
        super(t);
    }

    public static void throwException(String message) {
        throw new TortoiseException(message);
    }

    public static TortoiseException getException(String message) {
        throw new TortoiseException(message);
    }

    public static void throwException(Throwable t) {
        throw new TortoiseException(t);
    }
}
