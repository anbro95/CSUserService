package org.example.csuserservice.mapper;

import org.example.csuserservice.dto.UserRequest;
import org.example.csuserservice.model.User;
import org.mapstruct.Mapper;

@Mapper()
public interface UserMapper{

  User toEntity(UserRequest userRequest);

}
