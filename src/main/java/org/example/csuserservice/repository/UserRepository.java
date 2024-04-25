package org.example.csuserservice.repository;

import org.example.csuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  List<User> findUserByBirthDateBetween(LocalDate from, LocalDate to);
}
