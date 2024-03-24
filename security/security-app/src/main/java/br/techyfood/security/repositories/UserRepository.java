package br.techyfood.security.repositories;

import br.techyfood.security.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "SELECT * FROM users u WHERE u.username = :identity OR u.email = :identity", nativeQuery = true)
    UserEntity findByEmailOrUsername(@Param("identity") String identity);

    boolean existsByEmailOrUsername(String email, String username);

}
