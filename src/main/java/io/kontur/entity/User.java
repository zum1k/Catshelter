package io.kontur.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.kontur.utils.roleconverter.RoleConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User extends AbstractEntity<Long> {
  @Column(name = "login")
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "role")
  @Convert(converter = RoleConverter.class)
  private Role userType;
  @Column(name = "create_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL )
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonBackReference
  private List<Feeding> feedings = new ArrayList<>();
}
