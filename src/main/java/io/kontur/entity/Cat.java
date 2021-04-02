package io.kontur.entity;

import io.kontur.utils.AuditEntityDateListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "cat")
@Entity
public class Cat extends AbstractEntity<Long> {
  private String name;
  @Column(name = "сreate_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;
}
