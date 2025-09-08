package de.thm.mni.ima.framework;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import java.util.Objects;

/**
 * Base class for all API handlers.
 */
public abstract class ApiCallHandler {
  private Vertx vertx;

  /**
   * Creates a new instance of the ApiHandler.
   * @param vertx The Vertx instance.
   * @throws NullPointerException If the Vertx instance is null.
   */
  public ApiCallHandler(Vertx vertx) {
    Objects.requireNonNull(vertx, "Vertx must not be null.");
    this.vertx = vertx;
  }

  /**
   * @return The Vertx instance.
   */
  protected Vertx getVertx() {
    return vertx;
  }

  /**
   * @return The routing for the API.
   */
  public abstract Router getRouting();
}
