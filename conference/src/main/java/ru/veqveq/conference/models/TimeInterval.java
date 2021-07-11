package ru.veqveq.conference.models;

import ru.veqveq.conference.exceptions.TimeIntervalException;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeInterval {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public TimeInterval(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new TimeIntervalException(String.format("Error setting the time interval. Start: %s. End: %s", startTime, endTime));
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean intersection(TimeInterval timeInterval) {
        return !(this.startTime.isAfter(timeInterval.endTime)
                || this.startTime.equals(timeInterval.endTime)
                || this.endTime.isBefore(timeInterval.startTime)
                || this.endTime.equals(timeInterval.startTime));
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeInterval that = (TimeInterval) o;
        return Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
