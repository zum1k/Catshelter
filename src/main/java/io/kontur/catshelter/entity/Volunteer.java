package io.kontur.catshelter.entity;

import io.kontur.catshelter.entity.AbstractEntity;

public class Volunteer extends AbstractEntity<Long> {
  private String login;
  private String password;
  private String firstName;
  private String secondName;
  private String phoneNumber;

  public Volunteer(Long id) {
    super(id);
  }
}
