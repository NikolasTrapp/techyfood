package br.techyfood.productapp.services;

import br.techyfood.productapp.enities.ProductEntity;
import br.techyfood.productexternal.dtos.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductEntity findById(UUID id);

    ProductEntity create(ProductDto product);

    ProductEntity update(ProductDto product);

    ProductEntity update(UUID id, ProductDto product);

    void delete(ProductDto product);

    void delete(UUID id);

    List<ProductEntity> findAll();
    List<ProductEntity> findAllByRestaurantId(UUID restaurantId);
}
