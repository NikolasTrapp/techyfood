package br.techyfood.restaurantreplicated.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {


    @Id
    @NotNull(message = "id cannot be null.")
    private UUID id;

    @NotBlank(message = "username cannot be empty.")
    @Column(name = "username")
    private String username;

    @Email(message = "invalid email.")
    @NotBlank(message = "email cannot be empty.")
    @Column(name = "email")
    private String email;

}
