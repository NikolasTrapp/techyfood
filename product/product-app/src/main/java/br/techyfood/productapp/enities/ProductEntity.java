package br.techyfood.productapp.enities;

import br.techyfood.productreplicated.entities.RestaurantEntity;
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
@Table(name = "product")
public class ProductEntity {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "name cannot be empty")
    @Size(max = 70)
    @Column(name = "name")
    private String name;

    @NotBlank(message = "description cannot be empty")
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @NotNull
    @Builder.Default
    @Column(name = "active")
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price")
    private PriceEntity price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private RestaurantEntity restaurant;

}

