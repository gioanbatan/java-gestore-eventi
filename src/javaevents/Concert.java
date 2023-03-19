package javaevents;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event {
    // Fields
    LocalTime time;
    BigDecimal price;

    // Constructors
    public Concert(String title, String date, int capacity, LocalTime time, BigDecimal price) throws NegativeCapacityException {
        super(title, date, capacity);
        this.time = time;
        this.price = price;
    }

    // Getter and setters
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        return super.getDate().format(formatterDate) + " " + this.time.format(formatterTime);
    }

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("##,##€");
        return (df.format(this.price));
    }

    @Override
    public String toString() {
        return getFormattedDateTime() + " - " + super.getTitle() + " - " + getFormattedPrice();
    }
}