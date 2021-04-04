package io.kontur.entity;

import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "feeding")
@Entity
public class Feeding extends AbstractEntity<Long> {
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "cat_id")
  private Long catId;
  @Column(name = "feeding_time")
  private ZonedDateTime feedingTime;
}
