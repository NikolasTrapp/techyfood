package br.techyfood.security.repositories;

import br.techyfood.security.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findByAuthority(String authority);
}
