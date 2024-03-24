package br.example.security.services;

import br.example.security.dtos.UserDto;
import br.example.security.entities.UserEntity;

public interface UserService {

    UserEntity registerUser(UserDto user);
}
