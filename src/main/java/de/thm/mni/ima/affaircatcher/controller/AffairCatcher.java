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


    return 0; // TODO: Calculate the overlap percentage
  }

  /**
   * Calculates the affairs between a collection of employees.
   *
   * @param employees the collection of employees
   * @return the list of affair results ordered by the probability of an affair
   */
  public static List<AffairResult> computeAffairs(Collection<Employee> employees) {
    List<AffairResult> affairResults = new ArrayList<>();

    // TODO: Implement the affair calculation

    // TODO: Sort the affair results by the probability of an affair
    // you can use the sort method of list
    // The sort method requires a function (a, b) -> INT (negative if smaller, 0 if equal, positive if greater)


    return affairResults;
  }
}
