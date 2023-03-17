package javaevents;

public class CapacityReachedException extends RuntimeException {
    public CapacityReachedException(String message) {
        super(message);
    }
}
