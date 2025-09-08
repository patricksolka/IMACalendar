package de.thm.mni.ima.calendar.model;

/**
 * The EventType enum represents different types of events.
 * Each event type has a display name associated with it.
 */
public enum EventType {
  HOLIDAY("Holiday"),
  ILL("Ill"),
  BIRTHDAY("Birthday"),
  CONFERENCE("Conference"),
  WORKSHOP("Workshop");

  private final String displayName;

  /**
   * Creates a new EventType with the given display name.
   * @param displayName The display name of the event type.
   */
  EventType(String displayName) {
    this.displayName = displayName;
  }

  /**
   * @return The display name of the event type.
   */
  @Override
  public String toString() {
    return this.displayName;
  }
}
