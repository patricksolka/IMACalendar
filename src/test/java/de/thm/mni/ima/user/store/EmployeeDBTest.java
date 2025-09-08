package de.thm.mni.ima.user.store;

import de.thm.mni.ima.user.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDBTest {

  @AfterEach
  void tearDown() {
    var db = EmployeeStore.getInstance();
    db.clear();
  }

  @Test
  void save() {
    var db = EmployeeStore.getInstance();

    var user = new Employee("Max", "Mustermann", "Max.Mustermann@test.de", "HR");

    try {
      db.save(user);
      Assertions.assertTrue(true);
    } catch (Exception e) {
      fail("Saving a single user should not throw an exception");
    }
  }

  @Test
  void get() {
    var db = EmployeeStore.getInstance();

    var user = new Employee("Max", "Mustermann", "Max.Mustermann@test.de", "HR");

    try {
      db.save(user);
      Assertions.assertTrue(user.getId().isPresent(), "The user should have an id");
      var result = db.get(user.getId().get());
      Assertions.assertTrue(result.isPresent(), "The user should be present in the database");
      Assertions.assertEquals(user, result.get(), "The user should be the same as the one that was saved");
    } catch (Exception e) {
      fail("Getting a single user should not throw an exception", e);
    }
  }

  @Test
  void getAll() {
    var db = EmployeeStore.getInstance();

    var user1 = new Employee("Max", "Mustermann", "Max.Mustermann@test.de", "HR");
    var user2 = new Employee("Kim", "Musterfrau", "Kim.Musterfrau@test.de", "Sales");

    try {
      db.save(user1);
      db.save(user2);
      var result = db.getAll();
      Assertions.assertTrue(result.contains(user1), "The first user should be present in the database");
      Assertions.assertTrue(result.contains(user2), "The second user should be present in the database");
    } catch (Exception e) {
      fail("Getting all users should not throw an exception", e);
    }
  }

  @Test
  void find() {
    var db = EmployeeStore.getInstance();

    var user1 = new Employee("Max", "Mustermann", "Max.Mustermann@test.de", "HR");
    db.save(user1);

    var result = db.find(e -> e.getDepartment().equals("HR"));
    Assertions.assertTrue(result.contains(user1), "User should be found by its department in the database: " + result);
  }

  @Test
  void delete() {
    var db = EmployeeStore.getInstance();

    var user1 = new Employee("Max", "Mustermann", "Max.Mustermann@test.de", "HR");

    try {
      db.save(user1);
      db.delete(user1);
      var result = db.get(user1.getId().get());
      Assertions.assertTrue(result.isEmpty(), "The user should not be present in the database");
    } catch (Exception e) {
      fail("Deleting a single user should not throw an exception", e);
    }
  }
}
