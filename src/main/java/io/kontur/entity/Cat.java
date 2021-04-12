package io.kontur.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.kontur.utils.auditentity.AuditEntityDateListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Cat extends AbstractEntity<Long>  {
  @Column(name = "name")
  private String name;
  @Column(name = "create_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @OneToMany(mappedBy = "cat" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonBackReference
  private List<Feeding> feedings = new ArrayList<>();
}
