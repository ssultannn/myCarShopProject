package az.developia.CarsShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CarsShop.service.BasketService;

@RestController
@RequestMapping("/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;

	@PostMapping("/add/{carId}")
	public void addToBasket(@PathVariable Long carId) {
		basketService.addToBasket(carId);
	}
}
