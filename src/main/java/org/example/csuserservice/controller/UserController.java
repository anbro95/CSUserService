package org.example.csuserservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.csuserservice.dto.UserRequest;
import org.example.csuserservice.mapper.UserMapper;
import org.example.csuserservice.model.User;
import org.example.csuserservice.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;
  private final UserMapper  mapper;

  @PostMapping
  public User create(@RequestBody UserRequest user) {
    return service.create(mapper.toEntity(user));
  }

  @GetMapping
  public List<User> get(@RequestParam LocalDate from, @RequestParam LocalDate to) {
    return service.getByBirthDate(from, to);
  }

  @PutMapping
  public User update(@RequestBody User user) {
    return service.update(user);
  }

  @PatchMapping("/{id}")
  public User updateFields(@RequestBody User user, @PathVariable UUID id) {
    return service.updateFields(user, id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
