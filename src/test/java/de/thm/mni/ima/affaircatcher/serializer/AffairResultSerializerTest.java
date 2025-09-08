package de.thm.mni.ima.affaircatcher.serializer;

import de.thm.mni.ima.affaircatcher.model.AffairResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AffairResultSerializerTest {

  @Test
  void jsonSerialize() {
    var affairResult = new AffairResult("personA", "personB", 100);

    var jsonObject = AffairResultJsonSerializer.jsonSerialize(affairResult);

    assertEquals("personA", jsonObject.getString("personA"));
    assertEquals("personB", jsonObject.getString("personB"));
    assertEquals(100, jsonObject.getInteger("probability"));
  }
}
