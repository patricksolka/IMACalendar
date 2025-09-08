package de.thm.mni.ima.framework;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Store<T extends Entity> {

  /**
   * Stores the given entity into the store.
   * If an entity was already stored, it will be updated instead.
   *
   * @param e Entity to store.
   */
  void save(T e);

  /**
   * @param id Entity id
   * @return The entity if one was found having the given id, otherwise (if none found) returns empty.
   */
  Optional<T> get(long id);

  /**
   * @return All entities contained in this store.
   */
  Set<T> getAll();

  /**
   * Filters all existing entities based on the provided predicate.
   *
   * @param predicate A function that emits true, if and only if the entity should belong to the result set.
   * @return The result set containing all found entities, based on the provided predicate.
   */
  default Set<T> find(Predicate<T> predicate) {
    return getAll().stream().filter(predicate).collect(Collectors.toSet());
  }

  /**
   * Removes an entity with the given id from the store.
   * Does nothing if no element was found.
   *
   * @param id Entity id
   */
  void delete(long id);

  /**
   * Removes the given entity from the store.
   *
   * @param e Entity
   */
  default void delete(T e) {
    e.getId().ifPresent(this::delete);
  }
}
