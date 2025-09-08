package de.thm.mni.ima.affaircatcher.controller;

import de.thm.mni.ima.affaircatcher.serializer.AffairResultJsonSerializer;
import de.thm.mni.ima.user.store.EmployeeStore;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

/**
 * A handler for the API calls of the AffairCatcher.
 */
public class AffairCatcherApiCallHandler {

  /**
   * Handles the API call to get all affairs.
   * @param routingContext The routing context.
   */
  public void getAffairs(RoutingContext routingContext) {
    var db = EmployeeStore.getInstance();
    var employees = db.getAll();

    var affairResults = AffairCatcher.computeAffairs(employees);
    var result = new JsonArray(affairResults
      .stream()
      .map(AffairResultJsonSerializer::jsonSerialize)
      .toList()
    );
    routingContext.response().end(result.encode());
  }
}
