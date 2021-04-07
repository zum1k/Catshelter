package io.kontur.entity;

import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

//@EqualsAndHashCode(callSuper = true)
//@EntityListeners({AuditEntityDateListener.class})
//@Data
//@NoArgsConstructor
//@Table(name = "feeding")
//@Entity
public class Feeding extends AbstractEntity<Integer> {
//
//  @ManyToOne(optional = false, fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id", nullable = false)
//  @EqualsAndHashCode.Exclude
//  private User user;
//
//  @ManyToOne(optional = false, fetch = FetchType.LAZY)
//  @JoinColumn(name = "cat_id", nullable = false)
//  @EqualsAndHashCode.Exclude
//  private Cat cat;
//  @Column(name = "feeding_time")
//  private ZonedDateTime feedingTime;
}
