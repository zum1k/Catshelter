package io.kontur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractEntity<T extends Number> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private T id;
}
