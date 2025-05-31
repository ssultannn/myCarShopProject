package az.developia.CarsShop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.CarsShop.entity.BasketEntity;
import az.developia.CarsShop.entity.CarEntity;
import az.developia.CarsShop.entity.UserEntity;
import az.developia.CarsShop.jwt.SecurityUtil;
import az.developia.CarsShop.repository.BasketRepository;
import az.developia.CarsShop.repository.CarRepository;
import az.developia.CarsShop.repository.UserRepository;
import az.developia.CarsShop.response.BasketResponse;
import jakarta.transaction.Transactional;

@Service
public class BasketService {
	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	public void addToBasket(Long carId) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		BasketEntity basket = basketRepository.findByUserIdAndCarId(user.getId(), carId).orElse(new BasketEntity());
		basket.setUserId(user.getId());
		basket.setCarId(carId);
		if (basket.getQuantity() == null) {
			basket.setQuantity(1);
		} else {
			basket.setQuantity(basket.getQuantity() + 1);
		}
		basketRepository.save(basket);
	}

	@Transactional
	public void removeFromBasket(Long carId) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		basketRepository.deleteByUserIdAndCarId(user.getId(), carId);
	}

	public List<BasketResponse> getMyBasket() {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());
		List<BasketResponse> responses = baskets.stream().map(basket -> {
			CarEntity carEntity = carRepository.findById(basket.getCarId()).orElse(null);
			BasketResponse basketResponse = new BasketResponse();
			basketResponse.setId(carEntity.getId());
			basketResponse.setBrand(carEntity.getBrand());
			basketResponse.setModel(carEntity.getModel());
			basketResponse.setDescription(carEntity.getDescription());
			basketResponse.setPrice(carEntity.getPrice());
			basketResponse.setYear(carEntity.getYear());
			basketResponse.setImgUrl(carEntity.getImgUrl());
			basketResponse.setRating(carEntity.getRating());
			basketResponse.setOwnerId(carEntity.getOwnerId());
			basketResponse.setQuantity(basket.getQuantity());
			return basketResponse;
		}).collect(Collectors.toList());
		return responses;
	}
}
