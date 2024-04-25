package org.example.csuserservice.service.impl;

import org.example.csuserservice.exception.AgeRestrictedException;
import org.example.csuserservice.model.User;
import org.example.csuserservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

  @Mock
  private UserRepository repository;

  @InjectMocks
  private UserServiceImpl subj;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User().setId(UUID.randomUUID()).setAddress("Some street").setEmail("email@email.com").setFirstName("John").setLastName("Doe").setBirthDate(
      LocalDate.EPOCH);
  }

  @Test
  void create() {
    subj.create(user);

    verify(repository, times(1)).save(user);
  }

  @Test
  void create_AgeRestricted() {
    user.setBirthDate(LocalDate.now().minusYears(5));

    assertThrows(AgeRestrictedException.class, () -> subj.create(user));
  }

  @Test
  void update() {
    subj.update(user);
    verify(repository, times(1)).save(user);
  }

}
