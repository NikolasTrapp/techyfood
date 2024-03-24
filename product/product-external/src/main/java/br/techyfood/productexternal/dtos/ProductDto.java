package br.techyfood.productexternal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;

    @NotBlank
    private String name;

    private String description;

    private String image;

    private Boolean active = true;

    private CategoryDto category;

    private PriceDto price;

    private RestaurantDto restaurant;

}
