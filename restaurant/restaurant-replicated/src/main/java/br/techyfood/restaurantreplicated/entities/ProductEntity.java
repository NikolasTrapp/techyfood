package br.techyfood.restaurantreplicated.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ProductEntity {


    @Id
    private UUID id;

}
