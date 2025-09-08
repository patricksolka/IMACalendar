package de.thm.mni.ima.calendar.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The Event class represents an event with a start and end time, a subject, and a type.
 * An event object can be used to store and compare events.
 */
public class Event {
  private LocalDate start;
  private LocalDate end;
  private String subject;
  private EventType type;

  /**
   * Creates a new Event object with the given start time, end time, subject, and type.
   *
   * @param start the start time of the event
   * @param end the end time of the event
   * @param subject the subject or title of the event
   * @param type the type of the event, which is one of the EventType values
   */
  @JsonCreator
  public Event(
    @JsonProperty("start") LocalDate start,
    @JsonProperty("end") LocalDate end,
    @JsonProperty("subject") String subject,
    @JsonProperty("type") EventType type
  ) {
    if (start == null || end == null || subject == null || type == null) {
      throw new IllegalArgumentException("Arguments must not be null");
    }
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("Start date must not be after end date");
    }

    this.start = start;
    this.end = end;
    this.subject = subject;
    this.type = type;
  }

  /**
   * Returns the start time of the event.
   *
   * @return the start time of the event
   */
  public LocalDate getStart() {
    return start;
  }

  /**
   * Returns the end time of the event.
   *
   * @return the end time of the event
   */
  public LocalDate getEnd() {
    return end;
  }

  /**
   * Retrieves the subject or title of the event.
   *
   * @return the subject of the event as a string
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Returns the type of the event.
   *
   * @return the type of the event as an EventType value
   */
  public EventType getType() {
    return type;
  }

  /**
   * Calculates the duration of the event in days, including the start and end date.
   *
   * @return The duration of the event in days.
   */
  public int duration() {
    // since between is exclusive we increase it by 1
    return (int) ChronoUnit.DAYS.between(start, end) + 1;
  }

  /**
   * Determines whether this event overlaps with the given event.
   * Two events overlap if their time intervals intersect.
   *
   * <pre>
   * (2024-05-03 - 2024-05-10) (2024-05-03 - 2024-05-03) = true (start day overlaps)
   * (2024-05-03 - 2024-05-10) (2024-05-10 - 2024-05-15) = true (end day overlaps)
   * (2024-05-03 - 2024-05-10) (2024-05-05 - 2024-05-07) = true (included in event)
   * (2024-05-03 - 2024-05-10) (2024-05-05 - 2024-05-15) = true (intersects)
   * (2024-05-03 - 2024-05-10) (2024-05-11 - 2024-05-12) = false (does not intersect)
   *</pre>
   *
   * @param bEvent the event to compare with
   * @return true if there is an overlap, false otherwise
   */
  public boolean overlaps(Event bEvent) {
    return false; // TODO: implement correctly
  }

  /**
   * Calculates the number of overlapping days between this event and the given event.
   *
   * @param bEvent the event to compute the overlap with
   * @return the number of overlapping days, or 0 if there is no overlap
   */
  public int overlap(Event bEvent) {
    return 0; // TODO: implement correctly
  }

  /**
   * Finds the minimum of two LocalDate values.
   *
   * @param a the first LocalDate value
   * @param b the second LocalDate value
   * @return the minimum LocalDate value between a and b
   */
  private LocalDate min(LocalDate a, LocalDate b) {
    return LocalDate.of(2024, 1, 1); // TODO: implement correctly
  }


  /**
   * Finds the maximum of two LocalDate values.
   *
   * @param a the first LocalDate value
   * @param b the second LocalDate value
   * @return the maximum LocalDate value between a and b
   */
  private LocalDate max(LocalDate a, LocalDate b) {
    return LocalDate.of(2024, 1, 1); // TODO: implement correctly
  }

  @Override
  public String toString() {
    return "Event(" + start + ", " + end + ", " + subject + ", " + type + ')';
  }
}
