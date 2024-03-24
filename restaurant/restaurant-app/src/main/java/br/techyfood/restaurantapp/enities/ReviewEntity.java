package br.techyfood.restaurantapp.enities;

import br.techyfood.restaurantreplicated.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class ReviewEntity {

    @Id
    @NotNull(message = "id cannot be null.")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "description cannot be empty.")
    @Size(max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "stars cannot be null.")
    @Column(name = "stars", nullable = false, updatable = false)
    private Integer stars;

    @NotNull(message = "active cannot be null.")
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @NotNull(message = "restaurant cannot be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant", nullable = false, updatable = false)
    private RestaurantEntity restaurant;

    @NotNull(message = "created_at cannot be null.")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull(message = "user cannot be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
