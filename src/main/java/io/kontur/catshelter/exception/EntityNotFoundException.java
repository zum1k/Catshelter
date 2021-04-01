package io.kontur.catshelter.exception;

public class EntityNotFoundException extends RepositoryException {
  private final int errorCode = 40404;
  private final long entityId;

  public EntityNotFoundException(String entityName, long entityId) {
    super(entityName);
    this.entityId = entityId;
  }
}
