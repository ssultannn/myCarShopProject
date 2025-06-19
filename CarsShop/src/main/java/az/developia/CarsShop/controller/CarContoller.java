package az.developia.CarsShop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import az.developia.CarsShop.request.CarRequest;
import az.developia.CarsShop.service.CarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
@CrossOrigin("*")
public class CarContoller {
	@Autowired
	private CarService carService;

	@GetMapping
	public List<CarEntity> getAllCars() {
		return carService.getAllCars();
	}
	@GetMapping("/my-cars")
	public List<CarEntity> getMyCars() {
		return carService.getMyCars();
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
	@PreAuthorize("hasRole('ROLE_USER')")
	public void addCar(@RequestBody @Valid CarRequest carEntity) {
		carService.addCarForCurrentUser(carEntity);

	}

	@DeleteMapping("delete/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
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
	@PreAuthorize("hasRole('ROLE_USER')")
	public void updateCar(@PathVariable Long id, @RequestBody @Valid CarEntity car) {
		carService.uptadeCar(id, car);
	}
}
