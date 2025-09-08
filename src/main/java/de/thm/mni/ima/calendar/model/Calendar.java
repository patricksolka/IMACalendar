package de.thm.mni.ima.calendar.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * The Calendar class represents a calendar that stores a set of events.
 * Events can be added to the calendar, removed from the calendar, and retrieved from the calendar.
 */
public class Calendar {
  private Set<Event> events;

  /**
   * Constructs a new Calendar object.
   *
   * This constructor initializes the calendar with an empty set of events.
   * Events can be added to the calendar using the {@link #addEvent(Event)} method,
   * removed using the {@link #removeEvent(Event)} method, and retrieved using the
   * {@link #getEvents()} method.
   */
  public Calendar() {
    this.events = new HashSet<>();
  }

  /**
   * Constructs a new Calendar object with a shallow copy of given set of events.
   *
   * @param events the set of events to be added to the calendar
   */
  @JsonCreator
  public Calendar(@JsonProperty("events") Set<Event> events) {
    this.events = new HashSet<>(events);
  }

  /**
   * Returns a shallow copy of the set of events in the calendar.
   *
   * @return a shallow copy of the set of events in the calendar
   */
  public Set<Event> getEvents() {
    return new HashSet<>(events);
  }

  /**
   * Adds the given event to the calendar.
   *
   * @param event the event to be added to the calendar
   */
  public void addEvent(Event event) {
    events.add(event);
  }

  /**
   * Removes the given event from the calendar.
   *
   * @param event the event to be removed from the calendar
   */
  public void removeEvent(Event event) {
    events.remove(event);
  }
}
