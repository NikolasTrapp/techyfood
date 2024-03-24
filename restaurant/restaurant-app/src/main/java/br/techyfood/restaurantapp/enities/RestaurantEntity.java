package br.techyfood.restaurantapp.enities;

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
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(max = 90)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 14)
    @Column(name = "cnpj")
    private String cnpj;

    @NotNull
    @Builder.Default
    @Column(name = "active")
    private Boolean active = true;

    //TODO COLUNA PROPRIETARY, DEVERÁ SER REPLICADA DO SERVIÇO DE USUÁRIOS.
}
