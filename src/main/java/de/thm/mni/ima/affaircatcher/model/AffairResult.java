package de.thm.mni.ima.affaircatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the probability result of an affair between two people.
 */
public class AffairResult {

  private String personA;
  private String personB;
  private int probability;

  /**
   * Creates a new AffairResult with the given parameters.
   *
   * @param personA the first person
   * @param personB the second person
   * @param probability the probability of an affair between the two people
   */
  public AffairResult(
    @JsonProperty("personA") String personA,
    @JsonProperty("personB") String personB,
    @JsonProperty("probability") int probability
  ) {
    this.personA = personA;
    this.personB = personB;
    this.probability = probability;
  }

  /**
   * @return Returns the full name of the first person.
   */
  public String getPersonA() {
    return personA;
  }

  /**
   * @return Returns the full name of the second person.
   */
  public String getPersonB() {
    return personB;
  }

  /**
   * @return Returns the probability of an affair between the two people.
   */
  public int getProbability() {
    return probability;
  }
}
