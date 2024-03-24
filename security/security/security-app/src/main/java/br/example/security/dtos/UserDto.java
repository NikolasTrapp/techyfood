package br.example.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Email(message = "invalid email.")
    @NotBlank(message = "email cannot be empty.")
    private String email;

    @NotBlank(message = "username cannot be empty.")
    private String username;

    @NotBlank(message = "password cannot be empty.")
    private String password;
}
