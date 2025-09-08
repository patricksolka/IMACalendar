package de.thm.mni.ima.user.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thm.mni.ima.framework.Store;
import de.thm.mni.ima.user.model.Employee;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A crud store to manage the persistence of employees.
 */
public class EmployeeStore implements Store<Employee>, AutoCloseable {
  private final ConcurrentMap<String, byte[]> map;
  private final DB db;

  private ObjectMapper objectMapper;

  private static EmployeeStore instance;

  /**
   * @return The singleton instance of the employee store.
   */
  public static EmployeeStore getInstance() {
    if (instance == null) {
      instance = new EmployeeStore();
    }
    return instance;
  }

  private EmployeeStore() {
    this.db = DBMaker
      .fileDB("employees.db")
      .make();

    this.map = db
      .hashMap("map", Serializer.STRING, Serializer.BYTE_ARRAY)
      .createOrOpen();

    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new Jdk8Module());
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Override
  public void save(Employee e) {
    if (e.getId().isEmpty()) {
      e.setId(map.size() + 1);
    }

    try {
      map.put(String.valueOf(e.getId().get()), objectMapper.writeValueAsBytes(e));
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }

    db.commit();
  }

  @Override
  public Optional<Employee> get(long id) {
    return Optional
      .ofNullable(map.get(String.valueOf(id)))
      .map(data -> {
        try {
          return objectMapper.readValue(data, Employee.class);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
  }

  @Override
  public Set<Employee> getAll() {
    return map.values().stream()
      .flatMap(data -> {
        try {
          return Stream.of(objectMapper.readValue(data, Employee.class));
        } catch (IOException e) {
          return Stream.empty();
        }
      })
      .collect(Collectors.toSet());
  }

  @Override
  public Set<Employee> find(Predicate<Employee> predicate) {
    return Store.super.find(predicate);
  }

  @Override
  public void delete(long id) {
    map.remove(String.valueOf(id));
    db.commit();
  }

  @Override
  public void delete(Employee e) {
    Store.super.delete(e);
  }

  @Override
  public void close() throws Exception {
    db.close();
  }

  /**
   * Clears the store.
   */
  public void clear() {
    map.clear();
    db.commit();
  }
}
