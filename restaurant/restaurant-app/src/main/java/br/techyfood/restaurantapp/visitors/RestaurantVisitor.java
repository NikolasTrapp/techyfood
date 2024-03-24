package br.techyfood.restaurantapp.visitors;

import br.techyfood.restaurantapp.enities.RestaurantEntity;

public interface RestaurantVisitor {

    void visitBeforeCreate(RestaurantEntity entity);

    void visitBeforeUpdate(RestaurantEntity entity);

    void visitBeforeDelete(RestaurantEntity entity);

    void visitAfterCreate(RestaurantEntity entity);

    void visitAfterUpdate(RestaurantEntity entity);

    void visitAfterDelete(RestaurantEntity entity);

}
