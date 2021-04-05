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
@Table(name = "user")
@Entity
public class User extends AbstractEntity<Integer> {
  @Column(name = "login")
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String secondName;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "role")
  private Role userType;
  @Column(name = "create_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonBackReference
  private Set<Feeding> feedings = new HashSet<>();
}
