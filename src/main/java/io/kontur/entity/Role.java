package io.kontur.entity;

public enum Role {
  VOLUNTEER("volunteer"),
  ADMIN("admin");
  private final String value;

  Role(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Role getRole(String value) {
    if (value == null) {
      return null;
    }

    for (Role role : Role.values()) {
      if (value.equalsIgnoreCase(role.value)) {
        return role;
      }
    }
    return null;
  }
}
