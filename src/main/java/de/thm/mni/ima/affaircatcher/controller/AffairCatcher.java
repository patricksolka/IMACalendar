package de.thm.mni.ima.affaircatcher.controller;

import de.thm.mni.ima.affaircatcher.model.AffairResult;
import de.thm.mni.ima.calendar.model.Calendar;
import de.thm.mni.ima.calendar.model.EventType;
import de.thm.mni.ima.user.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The AffairCatcher class contains methods to calculate the overlap
 * percentage between two calendars and compute affairs between a collection of employees.
 */
public class AffairCatcher {

  /**
   * Calculates the overlap percentage between two calendars based on the holidays events.
   *
   * @param a the first calendar
   * @param b the second calendar
   * @return the overlap percentage as an integer
   */
  public static int overlapPercentage(Calendar a, Calendar b) {
    // Filter the events to only include holidays only
    var aEvents = a.getEvents().stream().filter(e -> e.getType() == EventType.HOLIDAY).toList();
    var bEvents = b.getEvents().stream().filter(e -> e.getType() == EventType.HOLIDAY).toList();

    int totalA = aEvents.stream().mapToInt(e -> e.duration()).sum();
    if (totalA == 0) return 0;

    int overlap = 0;
    for (var ea : aEvents) {
      for (var eb : bEvents) {
        overlap += ea.overlap(eb);
      }
    }
    return (int) Math.round(100.0 * overlap / totalA);
  }

  /**
   * Calculates the affairs between a collection of employees.
   *
   * @param employees the collection of employees
   * @return the list of affair results ordered by the probability of an affair
   */
  public static List<AffairResult> computeAffairs(Collection<Employee> employees) {
    List<AffairResult> affairResults = new ArrayList<>();
    var list = new ArrayList<>(employees);

    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < list.size(); j++) {
        if (i == j) continue;
        var a = list.get(i);
        var b = list.get(j);

        int probability = overlapPercentage(a.getCalendar(), b.getCalendar());
        affairResults.add(new AffairResult(
          a.getFname() + " " + a.getSname(),
          b.getFname() + " " + b.getSname(),
          probability
        ));
      }
    }

    affairResults.sort(java.util.Comparator.comparingInt(AffairResult::getProbability).reversed());
    return affairResults;
  }
}
