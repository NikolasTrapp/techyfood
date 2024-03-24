package br.techyfood.restaurantexternal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String cnpj;

    @NotNull
    private Boolean active = true;
}
