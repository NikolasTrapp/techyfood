package br.techyfood.productapp.repositories;

import br.techyfood.productapp.enities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {
}
