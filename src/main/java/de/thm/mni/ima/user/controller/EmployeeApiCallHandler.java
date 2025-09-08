package de.thm.mni.ima.user.controller;

import de.thm.mni.ima.user.serializer.EmployeeJsonSerializer;
import de.thm.mni.ima.user.store.EmployeeStore;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

/**
 * A handler for the API calls of the Employee.
 */
public class EmployeeApiCallHandler {

  private EmployeeStore employeeStore;


  /**
   * A handler for the API calls of the Employee.
   */
  public EmployeeApiCallHandler() {
    this.employeeStore = EmployeeStore.getInstance();
  }

  /**
   * Handles the API call to get an employee by its UID.
   * @param rc The routing context.
   */
  public void get(RoutingContext rc) {
    var uid = rc.pathParam("uid");

    if (uid == null || !uid.matches("[0-9]+")) {
      rc.response().setStatusCode(400).end();
      return;
    }

    var uidL = Long.parseLong(uid);

    var employee = employeeStore.get(uidL);

    if (employee.isEmpty()) {
      rc.response().setStatusCode(404).end();
      return;
    }

    rc
      .response()
      .setStatusCode(200)
      .end(
        EmployeeJsonSerializer.jsonSerialize(employee.get()).encode()
      );
  }

  /**
   * Handles the API call to get all employees.
   * @param rc The routing context.
   */
  public void getAll(RoutingContext rc) {
    var employees = employeeStore.getAll();
    var result = new JsonArray(
      employees.stream().map(EmployeeJsonSerializer::jsonSerialize).toList()
    );

    rc
      .response()
      .setStatusCode(200)
      .end(result.encode());
  }

  /**
   * Handles the API call to create a new employee.
   * @param rc The routing context.
   */
  public void create(RoutingContext rc) {
    var body = rc.body();

    if (body.isEmpty()) {
      rc.response().setStatusCode(400).end();
      return;
    }
    try {
      var employee = EmployeeJsonSerializer.deserialize(body.asJsonObject());
      employeeStore.save(employee);
      rc.response()
        .setStatusCode(201)
        .end(EmployeeJsonSerializer.jsonSerialize(employee).encode());
    } catch (Exception e) {
      rc.response().setStatusCode(400).end();
    }
  }

  /**
   * Handles the API call to update an employee.
   * @param rc The routing context.
   */
  public void update(RoutingContext rc) {
    var uid = rc.pathParam("uid");

    if (uid == null || !uid.matches("[0-9]+")) {
      rc.response().setStatusCode(400).end();
      return;
    }

    var uidL = Long.parseLong(uid);

    var body = rc.body();

    if (body.isEmpty()) {
      rc.response().setStatusCode(400).end();
      return;
    }

    try {
      var employee = EmployeeJsonSerializer.deserialize(body.asJsonObject());
      employee.setId(uidL);
      employeeStore.save(employee);
      rc.response()
        .setStatusCode(200)
        .end(EmployeeJsonSerializer.jsonSerialize(employee).encode());
    } catch (Exception e) {
      rc.response().setStatusCode(400).end();
    }
  }

  /**
   * Handles the API call to delete an employee.
   * @param rc The routing context.
   */
  public void delete(RoutingContext rc) {
    var uid = rc.pathParam("uid");

    if (uid == null || !uid.matches("[0-9]+")) {
      rc.response().setStatusCode(400).end();
      return;
    }

    var uidL = Long.parseLong(uid);

    employeeStore.delete(uidL);
    rc.response().setStatusCode(204).end();
  }
}
