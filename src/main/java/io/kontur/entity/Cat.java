package io.kontur.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "cat")
@Entity
public class Cat extends AbstractEntity<Integer> {
  @Column(name = "name")
  private String name;
  @Column(name = "create_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonBackReference
  private Set<Feeding> feedings = new HashSet<>();
}
