package io.kontur.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
  private Integer id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private String phoneNumber;
}
