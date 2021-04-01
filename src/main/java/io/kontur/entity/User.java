package io.kontur.entity;

import io.kontur.utils.AuditEntityDateListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User extends AbstractEntity<Long> {
  private String login;
  private String password;
  private String firstName;
  private String secondName;
  private String phoneNumber;
  private UserType userType;
}
