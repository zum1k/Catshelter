package io.kontur.catshelter.entity;

public abstract class AbstractEntity<T extends Number> {
  private T id;

  public AbstractEntity(T id) {
    this.id = id;
  }
}
