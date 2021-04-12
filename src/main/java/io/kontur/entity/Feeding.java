package io.kontur.entity;

import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "feedings")
@Entity
public class Feeding extends AbstractEntity<Long> {

  @ManyToOne (optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "cat_id" , nullable = false)
  @EqualsAndHashCode.Exclude
  private Cat cat;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id" , nullable = false)
  @EqualsAndHashCode.Exclude
  private User user;

  @Column(name = "feeding_time")
  private ZonedDateTime feedingTime;
}
