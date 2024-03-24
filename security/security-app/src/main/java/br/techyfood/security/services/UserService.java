package br.techyfood.security.services;

import br.techyfood.security.dtos.UserDto;
import br.techyfood.security.entities.UserEntity;

public interface UserService {

    UserEntity registerUser(UserDto user);
}
