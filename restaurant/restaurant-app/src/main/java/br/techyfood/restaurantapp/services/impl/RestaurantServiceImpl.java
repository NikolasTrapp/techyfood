package br.techyfood.restaurantapp.services.impl;

import br.techyfood.restaurantapp.enities.RestaurantEntity;
import br.techyfood.restaurantapp.exceptions.RestaurantException;
import br.techyfood.restaurantapp.repositories.RestaurantRepository;
import br.techyfood.restaurantapp.services.RestaurantService;
import br.techyfood.restaurantapp.visitors.RestaurantVisitor;
import br.techyfood.restaurantexternal.dtos.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static br.techyfood.core.Utils.convert;
import static br.techyfood.core.Utils.format;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantVisitor visitor;

    @Override
    @Transactional(readOnly = true)
    public RestaurantEntity findById(UUID id) {
        if (isNull(id)) {
            throw new RestaurantException("Restaurant ID cannot be null.");
        }
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RestaurantEntity create(RestaurantDto restaurant) {
        var restaurantEntity = convert(restaurant, RestaurantEntity.class);
        visitor.visitBeforeCreate(restaurantEntity);
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        visitor.visitAfterCreate(restaurantEntity);
        return restaurantEntity;
    }

    @Override
    @Transactional
    public RestaurantEntity update(RestaurantDto restaurant) {
        var restaurantEntity = convert(restaurant, RestaurantEntity.class);
        visitor.visitBeforeUpdate(restaurantEntity);
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        visitor.visitAfterUpdate(restaurantEntity);
        return restaurantEntity;
    }

    @Override
    @Transactional
    public RestaurantEntity update(UUID id, RestaurantDto restaurant) {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void delete(RestaurantDto restaurant) {
        delete(restaurant.getId());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var restaurantEntity = findById(id);
        visitor.visitBeforeDelete(restaurantEntity);
        if (isNull(restaurantEntity)) {
            throw new RestaurantException(format("Any restaurant found with id '{}'.", id));
        }

        restaurantRepository.deleteById(id);
        visitor.visitAfterDelete(restaurantEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantEntity> findAll() {
        return restaurantRepository.findAll();
    }
}
