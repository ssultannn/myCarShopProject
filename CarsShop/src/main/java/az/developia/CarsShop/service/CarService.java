package az.developia.CarsShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.CarsShop.entity.CarEntity;
import az.developia.CarsShop.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;

	public void addCar(CarEntity carEntity) {
		carRepository.save(carEntity);
	}

	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	public List<CarEntity> getAllCars() {
		return carRepository.findAll();
	}

	public Optional<CarEntity> getCarById(Long id) {
		return carRepository.findById(id);
	}

	public List<CarEntity> searchCars(String keyword) {
		return carRepository.findByBrandContainingIgnoreCase(keyword);
	}

	public List<CarEntity> filterByMinRating(Integer minRating) {
		return carRepository.findAllByRatingGreaterThanEqual(minRating);
	}

	public List<CarEntity> sortByPriceAsc() {
		return carRepository.findAllByOrderByPriceAsc();
	}

	public List<CarEntity> sortByPriceDesc() {
		return carRepository.findAllByOrderByPriceDesc();
	}

	public void uptadeCar(Long id, CarEntity carEntity) {
		Optional<CarEntity> oldCar = carRepository.findById(id);
		if (oldCar.isPresent()) {
			CarEntity car = oldCar.get();
			car.setBrand(carEntity.getBrand());
			car.setModel(carEntity.getModel());

			car.setDescription(carEntity.getDescription());

			car.setPrice(carEntity.getPrice());

			car.setYear(carEntity.getYear());

			car.setImgUrl(carEntity.getImgUrl());
			car.setRating(carEntity.getRating());
			car.setOwnerId(carEntity.getOwnerId());
			carRepository.save(car);

		}

	}
}
