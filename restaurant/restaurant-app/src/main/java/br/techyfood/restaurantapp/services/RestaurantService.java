package br.techyfood.restaurantapp.services;

import br.techyfood.restaurantapp.enities.RestaurantEntity;
import br.techyfood.restaurantexternal.dtos.RestaurantDto;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {

    RestaurantEntity findById(UUID id);

    RestaurantEntity create(RestaurantDto restaurant);

    RestaurantEntity update(RestaurantDto restaurant);

    RestaurantEntity update(UUID id, RestaurantDto restaurant);

    void delete(RestaurantDto restaurant);

    void delete(UUID id);

    List<RestaurantEntity> findAll();
}
