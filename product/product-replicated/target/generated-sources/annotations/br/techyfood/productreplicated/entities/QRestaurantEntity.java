package br.techyfood.productreplicated.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurantEntity is a Querydsl query type for RestaurantEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurantEntity extends EntityPathBase<RestaurantEntity> {

    private static final long serialVersionUID = -2143231042L;

    public static final QRestaurantEntity restaurantEntity = new QRestaurantEntity("restaurantEntity");

    public final BooleanPath active = createBoolean("active");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath name = createString("name");

    public QRestaurantEntity(String variable) {
        super(RestaurantEntity.class, forVariable(variable));
    }

    public QRestaurantEntity(Path<? extends RestaurantEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurantEntity(PathMetadata metadata) {
        super(RestaurantEntity.class, metadata);
    }

}

