package de.thm.mni.ima.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.thm.mni.ima.calendar.model.Calendar;
import de.thm.mni.ima.framework.Entity;

import java.util.Optional;

/**
 * Represents an employee of the company.
 */
public class Employee extends Entity {

  private String sname;
  private String fname;
  private String email;
  private String department;
  private Calendar calendar;

  /**
   * Creates a new employee with the given parameters.
   * @param fname First name of the employee
   * @param sname Second name of the employee
   * @param email Email of the employee
   * @param department Department of the employee
   */
  public Employee(
    String fname,
    String sname,
    String email,
    String department
  ) {
    this(fname, sname, email, department, new Calendar(), Optional.empty());
  }

  /**
   * Creates a new employee with the given parameters.
   *
   * @param fname First name of the employee
   * @param sname Second name of the employee
   * @param email Email of the employee
   * @param department Department of the employee
   * @param calendar Calendar of the employee
   *
   * @throws IllegalArgumentException If any of the parameters is null or blank
   */
  @JsonCreator
  public Employee(
    @JsonProperty("fname") String fname,
    @JsonProperty("sname") String sname,
    @JsonProperty("email") String email,
    @JsonProperty("department") String department,
    @JsonProperty("calendar") Calendar calendar,
    @JsonProperty("id") Optional<Long> id
    ) {
    if (fname == null || fname.isBlank()) {
      throw new IllegalArgumentException("First name must not be null or blank");
    }
    if (sname == null || sname.isBlank()) {
      throw new IllegalArgumentException("Second name must not be null or blank");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email must not be null or blank");
    }
    if (department == null || department.isBlank()) {
      throw new IllegalArgumentException("Department must not be null or blank");
    }
    if (calendar == null) {
      throw new IllegalArgumentException("Calendar must not be null");
    }

    this.fname = fname;
    this.sname = sname;
    this.email = email;
    this.department = department;
    this.calendar = calendar;
    if (id.isPresent()) {
      this.setId(id.get());
    }
  }

  public String getSname() {
    return sname;
  }

  public String getFname() {
    return fname;
  }

  public String getEmail() {
    return email;
  }

  public String getDepartment() {
    return department;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  @Override
  public String toString() {
    return String.format(
      "Employee(%s %s, %s, %s, %s, %d)",
      sname,
      fname,
      email,
      department,
      calendar,
      getId().orElse(0L)
    );
  }
}
