package de.thm.mni.ima.user.serializer;

import de.thm.mni.ima.framework.Serialization;
import de.thm.mni.ima.user.model.Employee;
import io.vertx.core.json.JsonObject;

/**
 * A serializer to serialize and deserialize employees to and from JSON.
 */
public final class EmployeeJsonSerializer {

  private EmployeeJsonSerializer() {}

  /**
   * Serializes an Employee to a JSON string.
   * @param object The Employee to serialize.
   * @return The serialized JSON string.
   * @throws RuntimeException If the serialization fails.
   */
  public static String serialize(Employee object) {
    try {
      return Serialization.createObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Serializes an Employee to a JsonObject.
   * @param object The Employee to serialize.
   * @return The serialized JsonObject.
   * @throws RuntimeException If the serialization fails.
   */
  public static JsonObject jsonSerialize(Employee object) {
    return new JsonObject(serialize(object));
  }

  /**
   * Deserializes a JSON string to an Employee.
   * @param json The JSON string to deserialize.
   * @return The deserialized Employee.
   * @throws RuntimeException If the deserialization fails.
   */
  public static Employee deserialize(String json) {
    try {
      return Serialization.createObjectMapper().readValue(json, Employee.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Deserializes a JsonObject to an Employee.
   * @param json The JsonObject to deserialize.
   * @return The deserialized Employee.
   * @throws RuntimeException If the deserialization fails.
   */
  public static Employee deserialize(JsonObject json) {
    return deserialize(json.encode());
  }
}
