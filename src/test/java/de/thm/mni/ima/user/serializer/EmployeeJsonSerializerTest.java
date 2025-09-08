package de.thm.mni.ima.user.serializer;

import de.thm.mni.ima.user.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeJsonSerializerTest {

  @Test
  void jsonSerialize() {
    var employee = new Employee("Max", "Mustermann", "test@test.de", "HR");

    var json = EmployeeJsonSerializer.jsonSerialize(employee);

    assertEquals(employee, EmployeeJsonSerializer.deserialize(json), "The deserialized employee should be the same as the original");
  }
}
