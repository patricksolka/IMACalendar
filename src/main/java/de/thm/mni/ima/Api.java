package de.thm.mni.ima;

import de.thm.mni.ima.affaircatcher.controller.AffairCatcherApiCallHandler;
import de.thm.mni.ima.user.controller.EmployeeApiCallHandler;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * The API class is responsible for setting up the routing for the API.
 */
public class Api {
  private final Vertx vertx;

  /**
   * Creates a new instance of the API.
   * @param vertx The Vertx instance.
   */
  public Api(Vertx vertx) {
    this.vertx = vertx;
  }

  /**
   * @return The pre-configured router that handles the routing for all API calls.
   */
  public Handler<HttpServerRequest> getRouter() {
    var router = Router.router(vertx);

    // Register body handler on all route to download body, making it available at the start of further handlers.
    router.route().handler(BodyHandler.create());

    // AffairCatcher
    var affairCatcherApiCallHandler = new AffairCatcherApiCallHandler();
    router.route("/affairCatcher").handler(affairCatcherApiCallHandler::getAffairs);

    // Employees
    var employeeController = new EmployeeApiCallHandler();
    router.get("/employees/:uid").handler(employeeController::get);
    router.get("/employees").handler(employeeController::getAll);
    router.post("/employees").handler(employeeController::create);
    router.put("/employees/:uid").handler(employeeController::update);
    router.delete("/employees/:uid").handler(employeeController::delete);

    return router;
  }
}
