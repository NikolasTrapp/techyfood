package br.techyfood.security.controller;

import br.techyfood.security.dtos.UserDto;
import br.techyfood.security.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.techyfood.core.Utils.convert;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody @Valid UserDto user) {
        return ResponseEntity.ok(convert(userService.registerUser(user), UserDto.class));
    }

}
