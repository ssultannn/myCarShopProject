package az.developia.CarsShop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.CarsShop.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
Optional<UserEntity> findByUsername(String username);
}
