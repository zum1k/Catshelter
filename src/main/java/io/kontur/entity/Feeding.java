package io.kontur.entity;

import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "feeding")
@Entity
public class Feeding extends AbstractEntity<Long> {
  private Long userId;
  private Long catId;
  private ZonedDateTime feedingTime;
}
