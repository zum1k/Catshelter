package io.kontur.catshelter.entity;

public class User extends AbstractEntity<Long> {
  private String login;
  private String password;
  private String firstName;
  private String secondName;
  private String phoneNumber;
  private UserType userType;

  public User(Long id) {
    super(id);
  }
}
