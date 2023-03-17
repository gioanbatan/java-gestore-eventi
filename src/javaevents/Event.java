package javaevents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    // Fields
    private String title;
    private LocalDate date;
    private int capacity;
    private int booked;

    // Contructors
    public Event(String title, String date, int capacity) {
        // Actual date
        LocalDate localDate = LocalDate.now();

        // Title
        this.title = title;

        // Date
        // String conversion
        LocalDate parsedDate = LocalDate.parse(date);
        // Assignment
        if (dateIsValid(parsedDate)) {
            this.date = parsedDate;
        }

        // Capacity
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new NegativeCapacityException("Capacity int must be > 0.");
        }

        // Booked
        this.booked = 0;
    }

    // Getter and setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooked() {
        return booked;
    }

    // Methods
    public void reserveDate() {
        if (dateIsValid(date) && booked < capacity) {
            booked++;
        } else {
            throw new CapacityReachedException("You can't add more booked, capacity: " + capacity + ", booked : " + booked + ".");
        }
    }

    public void cancelDate() {
        if (booked > 0) {
            booked--;
        } else {
            throw new CancelNoBookedException("There's no book.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return (date.format(formatter) + " - " + this.title);
    }

    // Service methods
    private static boolean dateIsValid(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new DateInPastException("Date can't be in past. Actual date: " + LocalDate.now() + ", your date: " + date + ".");
        }
        return true;
    }
}

