package io.kontur.exception;

import lombok.Getter;

@Getter
public class EntityNotAddedException extends RepositoryException {
  private final int errorCode = 40401;

  public EntityNotAddedException(String entityName) {
    super(entityName);
  }

  public int getErrorCode() {
    return this.errorCode;
  }
}
