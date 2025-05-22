package az.developia.CarsShop.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.CarsShop.entity.CarEntity;
import az.developia.CarsShop.entity.UserEntity;
import az.developia.CarsShop.jwt.SecurityUtil;
import az.developia.CarsShop.repository.CarRepository;
import az.developia.CarsShop.repository.UserRepository;
import az.developia.CarsShop.request.CarRequest;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;

	public void addCarForCurrentUser(CarRequest carRequest) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
		CarEntity carEntity = new CarEntity();
		carEntity.setBrand(carRequest.getBrand());
		carEntity.setModel(carRequest.getModel());

		carEntity.setDescription(carRequest.getDescription());

		carEntity.setPrice(carRequest.getPrice());

		carEntity.setYear(carRequest.getYear());

		carEntity.setImgUrl(carRequest.getImgUrl());
		carEntity.setRating(carRequest.getRating());
		carEntity.setOwnerId(userEntity.getId());
		carRepository.save(carEntity);
	}

	public List<CarEntity> getMyCars() {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
		return carRepository.findByOwnerId(userEntity.getId());
	}

	public CarEntity getMyCarById(Long id) {
		String username = SecurityUtil.getCurrentUsername();
		CarEntity carEntity = carRepository.findById(id).orElseThrow(() -> new RuntimeException());
		Long currentUserId = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException()).getId();

		if (!carEntity.getOwnerId().equals(currentUserId)) {
			throw new RuntimeException("");
		}
		return carEntity;
	}

	public void deleteCar(Long id) {
		CarEntity carEntity = getMyCarById(id);
		carRepository.delete(carEntity);
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
		Optional<CarEntity> oldCar = Optional.ofNullable(getMyCarById(id));
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
