package de.thm.mni.ima.framework;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents an entity that can be stored in a store.
 */
public abstract class Entity {
  private Optional<Long> id;

  /**
   * Creates a new entity with the given id.
   * @param id Entity id
   */
  public Entity(long id) {
    this.id = Optional.of(id);
  }

  /**
   * Creates a new entity without an id.
   */
  public Entity() {
    this.id = Optional.empty();
  }

  /**
   * Sets the id of this entity.
   * @param id Entity id
   */
  public void setId(long id) {
    this.id = Optional.of(id);
  }

  /**
   * @return The id of this entity if it has one, otherwise returns empty.
   */
  public Optional<Long> getId() {
    return id;
  }
}
