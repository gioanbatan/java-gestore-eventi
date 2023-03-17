package javaevents;

public class CancelNoBookedException extends RuntimeException {
    public CancelNoBookedException(String message) {
        super(message);
    }
}
