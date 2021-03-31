package io.kontur.catshelter.entity;

import io.kontur.catshelter.entity.AbstractEntity;

public class Cat extends AbstractEntity<Long> {
  private String name;

  public Cat(Long id) {
    super(id);
  }
}
