package org.example.csuserservice.service;

import org.example.csuserservice.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserService {

  User create(User user);

  User update(User user);

  void delete(UUID id);

  List<User> getByBirthDate(LocalDate from, LocalDate to);

  User updateFields(User user, UUID id);
}
