package org.example.csuserservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.csuserservice.exception.AgeRestrictedException;
import org.example.csuserservice.exception.InvalidDateException;
import org.example.csuserservice.exception.NotFoundException;
import org.example.csuserservice.model.User;
import org.example.csuserservice.repository.UserRepository;
import org.example.csuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Value("${age}")
  private int ALLOWED_AGE;

  @Override
  public User create(User user) {
    validateAge(user.getBirthDate());

    return repository.save(user);
  }

  @Override
  public User update(User user) {
    return repository.save(user);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }

  @Override
  public List<User> getByBirthDate(LocalDate from, LocalDate to) {
    checkDateInput(from, to);
    return repository.findUserByBirthDateBetween(from, to);
  }

  @Override
  public User updateFields(User user, UUID uuid) {
    User updatedUser = repository.findById(uuid).orElseThrow(() -> new NotFoundException("Requested user was not found"));

    if (user.getBirthDate() != null) updatedUser.setBirthDate(user.getBirthDate());
    if (user.getFirstName() != null) updatedUser.setFirstName(user.getFirstName());
    if (user.getLastName() != null) updatedUser.setLastName(user.getLastName());
    if (user.getEmail() != null) updatedUser.setEmail(user.getEmail());
    if (user.getPhoneNumber() != null) updatedUser.setPhoneNumber(user.getPhoneNumber());
    if (user.getAddress() != null) updatedUser.setAddress(user.getAddress());

    return repository.save(updatedUser);
  }

  private void checkDateInput(LocalDate from, LocalDate to) {
    if (!from.isBefore(to)) {
      throw new InvalidDateException("Date from must be 'before' the date 'to'");
    }
  }

  private void validateAge(LocalDate birthDate) {
    LocalDate allowedBirthDate = LocalDate.now().minusYears(ALLOWED_AGE);

    if (allowedBirthDate.isBefore(birthDate)) {
      throw new AgeRestrictedException("Your age is below " + ALLOWED_AGE);
    }
  }
}
