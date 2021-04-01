package io.kontur.catshelter.exception;

public class RepositoryException extends RuntimeException {
  public RepositoryException(String entityName) {
    super(entityName);
  }
}
