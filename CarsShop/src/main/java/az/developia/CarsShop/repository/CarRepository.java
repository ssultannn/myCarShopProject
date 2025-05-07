package az.developia.CarsShop.repository;

import java.util.List;import org.apache.catalina.startup.Catalina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.CarsShop.entity.CarEntity;
@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
	List<CarEntity> findByOwnerId(Long ownerId);

	List<CarEntity> findAllByOrderByPriceAsc();

	List<CarEntity> findAllByOrderByPriceDesc();
	
	List<CarEntity> findByBrandContainingIgnoreCase(String brand);
	
	List<CarEntity> findAllByOrderByRatingDesc();
	
	List<CarEntity> findAllByRatingGreaterThanEqual(Integer minRating);
}
