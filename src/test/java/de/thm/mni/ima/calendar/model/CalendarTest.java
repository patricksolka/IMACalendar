package de.thm.mni.ima.calendar.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

  @Test
  void events_crud() {
    var calendar = new Calendar();

    assertEquals(0, calendar.getEvents().size());

    var event = new Event(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15), "Holiday Event 0", EventType.HOLIDAY);

    calendar.addEvent(event);

    assertEquals(1, calendar.getEvents().size());
    assertTrue(calendar.getEvents().contains(event));

    assertEquals(event, calendar.getEvents().iterator().next());

    calendar.removeEvent(event);

    assertEquals(0, calendar.getEvents().size());
  }
}
