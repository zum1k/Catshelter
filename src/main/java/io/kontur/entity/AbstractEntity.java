package io.kontur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractEntity<T extends Number> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private T id;
  @Column(name = "сreate_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  public AbstractEntity(T id) {
    this.id = id;
  }
}
