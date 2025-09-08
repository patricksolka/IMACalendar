package de.thm.mni.ima.affaircatcher.controller;

import de.thm.mni.ima.calendar.model.Calendar;
import de.thm.mni.ima.calendar.model.Event;
import de.thm.mni.ima.calendar.model.EventType;
import de.thm.mni.ima.user.model.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AffairCatcherTest {

  @Test
  void overlapPercentage() {
    int overlapPercentage = AffairCatcher.overlapPercentage(new Calendar(
      Set.of(
        new Event(LocalDate.of(2024,  1, 1), LocalDate.of(2024, 1, 15), "Holiday Event 0", EventType.HOLIDAY),
        new Event(LocalDate.of(2024, 3, 7), LocalDate.of(2024, 3, 20), "Holiday Event 1", EventType.HOLIDAY)
      )
    ), new Calendar(
      Set.of(
        new Event(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15), "Holiday Event 0", EventType.HOLIDAY),
        new Event(LocalDate.of(2024, 3, 9), LocalDate.of(2024, 3, 30), "Holiday Event 1", EventType.HOLIDAY)
      )
    ));

    assertEquals(93, overlapPercentage);
  }

  @Test
  void computeAffairs() {
    var employees = List.of(
      new Employee("Tim", "Muscleman", "Tim.Muscleman@www.de", "IT-Security", new Calendar(
        Set.of(
          new Event(LocalDate.of(2024,  1, 1), LocalDate.of(2024, 1, 15), "Holiday Event 0", EventType.HOLIDAY),
          new Event(LocalDate.of(2024, 3, 7), LocalDate.of(2024, 3, 20), "Holiday Event 1", EventType.HOLIDAY)
        )
      ), Optional.empty()),
      new Employee("Kim", "Panse", "Kim.Panse@www.de", "Sales", new Calendar(
        Set.of(
          new Event(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15), "Holiday Event 0", EventType.HOLIDAY),
          new Event(LocalDate.of(2024, 3, 9), LocalDate.of(2024, 3, 30), "Holiday Event 1", EventType.HOLIDAY)
        )
      ), Optional.empty()),
      new Employee("Anna", "Nass", "Anna.Nass@www.de", "HR", new Calendar(
        Set.of(
          new Event(LocalDate.of(2023, 3, 3), LocalDate.of(2023, 5, 2), "Holiday Event 0", EventType.HOLIDAY),
          new Event(LocalDate.of(2023, 12, 24), LocalDate.of(2024, 3, 2), "Holiday Event 1", EventType.HOLIDAY)
        )
      ), Optional.empty())
    );

    var result = AffairCatcher.computeAffairs(employees);

    assertEquals(6, result.size());

    assertEquals("Tim Muscleman", result.get(0).getPersonA());
    assertEquals("Kim Panse", result.get(0).getPersonB());
    assertEquals(93, result.get(0).getProbability());

    assertEquals("Kim Panse", result.get(1).getPersonA());
    assertEquals("Tim Muscleman", result.get(1).getPersonB());
    assertEquals(73, result.get(1).getProbability());

    assertEquals("Tim Muscleman", result.get(2).getPersonA());
    assertEquals("Anna Nass", result.get(2).getPersonB());
    assertEquals(52, result.get(2).getProbability());

    assertEquals("Kim Panse", result.get(3).getPersonA());
    assertEquals("Anna Nass", result.get(3).getPersonB());
    assertEquals(41, result.get(3).getProbability());

    assertEquals("Anna Nass", result.get(4).getPersonA());
    assertEquals("Tim Muscleman", result.get(4).getPersonB());
    assertEquals(11, result.get(4).getProbability());

    assertEquals("Anna Nass", result.get(5).getPersonA());
    assertEquals("Kim Panse", result.get(5).getPersonB());
    assertEquals(11, result.get(5).getProbability());
  }
}
