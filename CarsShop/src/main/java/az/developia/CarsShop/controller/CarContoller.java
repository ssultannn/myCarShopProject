package az.developia.CarsShop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CarsShop.entity.CarEntity;
import az.developia.CarsShop.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarContoller {
	@Autowired
	private CarService carService;

	@GetMapping
	public List<CarEntity> getAllCars() {
		return carService.getAllCars();
	}

	@GetMapping("/{id}")
	public Optional<CarEntity> getCar(@PathVariable Long id) {
		return carService.getCarById(id);
	}

	@GetMapping("/search")
	public List<CarEntity> searchCars(@RequestParam String keyword) {
		return carService.searchCars(keyword);
	}

	@PostMapping("/add")
	public void addCar(@RequestBody CarEntity carEntity) {
		carService.addCar(carEntity);

	}

	@DeleteMapping("delete/{id}")
	public void deleteCar(@PathVariable Long id) {
		carService.deleteCar(id);
	}

	@GetMapping("/rating")
	public List<CarEntity> byRating(@RequestParam Integer minRating) {
		return carService.filterByMinRating(minRating);
	}

	@GetMapping("/sort-by-price-asc")
	public List<CarEntity> byPriceAsc() {
		return carService.sortByPriceAsc();

	}

	@GetMapping("/sort-by-price-desc")
	public List<CarEntity> byPriceDesc() {
		return carService.sortByPriceDesc();

	}

	@PutMapping("/update/{id}")
	public void updateCar(@PathVariable Long id, @RequestBody CarEntity car) {
		carService.uptadeCar(id, car);
	}
}
