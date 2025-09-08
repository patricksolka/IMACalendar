package de.thm.mni.ima.calendar.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

  @Test
  void duration_oneday() {
    var event = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 3),
      "Test", EventType.HOLIDAY);

    assertEquals(1, event.duration());
  }

  @Test
  void duration_multiday() {
    var event = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    assertEquals(3, event.duration());
  }

  @Test
  void duration_over_years() {
    var event = new Event(
      LocalDate.of(2023, 12, 3),
      LocalDate.of(2024, 1, 5),
      "Test", EventType.HOLIDAY);

    assertEquals(34, event.duration());
  }

  @Test
  void overlaps_at_end() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 5),
      LocalDate.of(2024, 3, 6),
      "Test", EventType.HOLIDAY);

    assertTrue(eventA.overlaps(eventB));
    assertTrue(eventB.overlaps(eventA));
  }

  @Test
  void overlaps_at_start() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 1),
      LocalDate.of(2024, 3, 3),
      "Test", EventType.HOLIDAY);

    assertTrue(eventA.overlaps(eventB));
    assertTrue(eventB.overlaps(eventA));
  }

  @Test
  void overlaps_at_middle() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 10),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 4),
      LocalDate.of(2024, 3, 6),
      "Test", EventType.HOLIDAY);

    assertTrue(eventA.overlaps(eventB));
    assertTrue(eventB.overlaps(eventA));
  }

  @Test
  void overlaps_no_overlap() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 6),
      LocalDate.of(2024, 3, 7),
      "Test", EventType.HOLIDAY);

    assertFalse(eventA.overlaps(eventB));
    assertFalse(eventB.overlaps(eventA));
  }

  @Test
  void overlap_at_start() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 1),
      LocalDate.of(2024, 3, 3),
      "Test", EventType.HOLIDAY);

    assertEquals(1, eventA.overlap(eventB));
    assertEquals(1, eventB.overlap(eventA));
  }

  @Test
  void overlap_at_end() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 5),
      LocalDate.of(2024, 3, 6),
      "Test", EventType.HOLIDAY);

    assertEquals(1, eventA.overlap(eventB));
    assertEquals(1, eventB.overlap(eventA));
  }

  @Test
  void overlap() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 4),
      LocalDate.of(2024, 3, 6),
      "Test", EventType.HOLIDAY);

    assertEquals(2, eventA.overlap(eventB));
    assertEquals(2, eventB.overlap(eventA));
  }

  @Test
  void overlap_over_years() {
    var eventA = new Event(
      LocalDate.of(2023, 12, 31),
      LocalDate.of(2024, 1, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 1, 4),
      LocalDate.of(2024, 1, 6),
      "Test", EventType.HOLIDAY);

    assertEquals(2, eventA.overlap(eventB));
    assertEquals(2, eventB.overlap(eventA));
  }

  @Test
  void overlap_no_overlap() {
    var eventA = new Event(
      LocalDate.of(2024, 3, 3),
      LocalDate.of(2024, 3, 5),
      "Test", EventType.HOLIDAY);

    var eventB = new Event(
      LocalDate.of(2024, 3, 6),
      LocalDate.of(2024, 3, 7),
      "Test", EventType.HOLIDAY);

    assertEquals(0, eventA.overlap(eventB));
    assertEquals(0, eventB.overlap(eventA));
  }
}
