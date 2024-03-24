package br.techyfood.restaurantapp.enities;

import br.techyfood.restaurantreplicated.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @NotNull(message = "id cannot be null.")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "name cannot be empty.")
    @Size(max = 90)
    @Column(name = "name")
    private String name;

    @NotBlank(message = "cnpj cannot be empty.")
    @Size(max = 14)
    @Column(name = "cnpj")
    private String cnpj;

    @NotNull(message = "active cannot be null.")
    @Builder.Default
    @Column(name = "active")
    private Boolean active = true;

    @NotNull(message = "user cannot be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
