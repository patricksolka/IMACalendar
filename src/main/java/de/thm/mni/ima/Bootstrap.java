package de.thm.mni.ima;

import de.thm.mni.ima.calendar.model.Calendar;
import de.thm.mni.ima.calendar.model.Event;
import de.thm.mni.ima.calendar.model.EventType;
import de.thm.mni.ima.user.model.Employee;
import de.thm.mni.ima.user.store.EmployeeStore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Initializes the data storage with some test data.
 */
public final class Bootstrap {

  /**
   * Initializes the data storage with some test data.
   *
   * @throws IOException If the data storage could not be initialized
   */
  public static void initializeDataStorage() throws IOException {
    var employeeStore = EmployeeStore.getInstance();
    employeeStore.clear();
    testData().forEach(employeeStore::save);
  }

  private static List<Employee> testData() {
    return List.of(
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
  }
}
