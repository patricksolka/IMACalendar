package de.thm.mni.ima.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A utility class to create and configure ObjectMappers for serialization and deserialization.
 */
public final class Serialization {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  static {
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.registerModule(new JavaTimeModule());
  }

  private Serialization() {}

  /**
   * Creates a new ObjectMapper with the default configuration.
   * @return The created ObjectMapper.
   */
  public static ObjectMapper createObjectMapper() {
    return objectMapper;
  }
}
