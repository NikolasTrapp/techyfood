package br.example.security.services.impl;

import br.example.security.dtos.UserDto;
import br.example.security.entities.RoleEntity;
import br.example.security.entities.UserEntity;
import br.example.security.exceptions.RegisterException;
import br.example.security.repositories.RoleRepository;
import br.example.security.repositories.UserRepository;
import br.example.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.techyfood.core.Utils.format;
import static br.techyfood.core.Utils.isEmpty;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_AUTHORITY = "user";
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 60;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity registerUser(UserDto user) {
        var userEntity = buildUser(user);
        return userRepository.save(userEntity);
    }

    private UserEntity buildUser(UserDto user) {
        validateUser(user);
        var password = passwordEncoder.encode(user.getPassword());
        var roleEntity = RoleEntity.builder()
                .authority(USER_AUTHORITY)
                .build();
        var authorities = Set.of(roleEntity);

        return UserEntity.builder()
                .username(user.getUsername())
                .authorities(authorities)
                .email(user.getEmail())
                .password(password)
                .build();
    }

    private void validateUser(UserDto user) throws RegisterException {
        validatePassword(user.getPassword());
        validateUsername(user.getUsername());
        validateEmail(user.getEmail());
        var userExists = userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername());

        if (userExists) {
            throw new RegisterException(format("An user already exists with provided username or email."));
        }
    }

    private void validatePassword(String password) throws RegisterException {
        if (isEmpty(password)) {
            throw new RegisterException("Password cannot be empty");
        }

        if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
            throw new RegisterException(format("Password length must be between {} and {} characters", MIN_LENGTH, MAX_LENGTH));
        }

        if (!password.matches(".*\\d.*")) {
            throw new RegisterException("Password must contain at least one digit");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new RegisterException("Password must contain at least one uppercase letter");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new RegisterException("Password must contain at least one lowercase letter");
        }

        if (!password.matches(".*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?].*")) {
            throw new RegisterException("Password must contain at least one special character");
        }
    }

    private void validateUsername(String username) throws RegisterException {
        if (isEmpty(username)) {
            throw new RegisterException("Username cannot be empty");
        }

        if (username.length() < MIN_LENGTH || username.length() > MAX_LENGTH) {
            throw new RegisterException(format("Username length must be between {} and {} characters", MIN_LENGTH, MAX_LENGTH));
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new RegisterException("Username must contain only alphanumeric characters");
        }

        if (username.contains(" ")) {
            throw new RegisterException("Username cannot contain spaces");
        }

        if (username.matches("")) {
            throw new RegisterException("Username cannot contain special characters");
        }
    }

    private void validateEmail(String email) throws RegisterException {
        if (isEmpty(email)) {
            throw new RegisterException("Email cannot be empty");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new RegisterException("Invalid email format");
        }
    }
}
