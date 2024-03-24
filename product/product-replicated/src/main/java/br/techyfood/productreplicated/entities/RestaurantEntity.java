package br.techyfood.productreplicated.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @NotNull
    @Column(name = "id")
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "active")
    private Boolean active;

}
