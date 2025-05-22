package az.developia.CarsShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.CarsShop.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
	List<BasketEntity> findByUserId(Long userId);

	Optional<BasketEntity> findByUserIdAndCarId(Long userId, Long carId);

	void deleteByUserIdAndCarId(Long userId, Long carId);
}
