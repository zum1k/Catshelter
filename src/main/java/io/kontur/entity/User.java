package io.kontur.entity;

import io.kontur.utils.AuditEntityDateListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@EntityListeners({AuditEntityDateListener.class})
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
  @Column(name = "second_name")
  private String secondName;
  @Column(name = "phone_number")
  private String phoneNumber;
  private UserType userType;
  @Column(name = "сreate_date")
  private ZonedDateTime createDate;
  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;
}
