package org.example.csuserservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

import static org.example.csuserservice.util.Constants.EMAIL_REGEXP;

public record UserRequest(

  @NotNull
  @Pattern(regexp = EMAIL_REGEXP)
  String email,

  @NotNull
  String firstName,

  @NotNull
  String lastName,

  @NotNull
  LocalDate birthDate,

  String address,

  String phoneNumber
) {
}
