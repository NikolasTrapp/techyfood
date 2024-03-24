package br.techyfood.productapp.repositories;

import br.techyfood.productapp.enities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "SELECT * FROM product pe WHERE pe.restaurant = :empresaId", nativeQuery = true)
    List<ProductEntity> findAllByRestaurant(@Param("empresaId") UUID id);
}
