package de.thm.mni.ima.affaircatcher.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.thm.mni.ima.affaircatcher.model.AffairResult;
import de.thm.mni.ima.framework.Serialization;
import io.vertx.core.json.JsonObject;

/**
 * A serializer to serialize and deserialize affair results to and from JSON.
 */
public final class AffairResultJsonSerializer {

  private AffairResultJsonSerializer() {}

  /**
   * Serializes an AffairResult to a JSON object.
   * @param affairResult The AffairResult to serialize.
   * @return The serialized JSON object.
   * @throws RuntimeException If the serialization fails.
   */
  public static JsonObject jsonSerialize(AffairResult affairResult) {
    var objectMapper = Serialization.createObjectMapper();

      try {
          return new JsonObject(objectMapper.writeValueAsString(affairResult));
      } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
      }
  }
}
