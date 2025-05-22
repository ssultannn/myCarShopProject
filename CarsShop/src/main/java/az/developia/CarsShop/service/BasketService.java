package az.developia.CarsShop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.CarsShop.entity.BasketEntity;
import az.developia.CarsShop.entity.UserEntity;
import az.developia.CarsShop.jwt.SecurityUtil;
import az.developia.CarsShop.repository.BasketRepository;
import az.developia.CarsShop.repository.CarRepository;
import az.developia.CarsShop.repository.UserRepository;

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
		BasketEntity basket = basketRepository.findByUserIdAndCarId(user.getId(),carId).orElse(new BasketEntity());
		basket.setUserId(user.getId());
		basket.setCarId(carId);
		if(basket.getQuantity()==null) {
			basket.setQuantity(1);
		}else {
			basket.setQuantity(basket.getQuantity()+1);
		}
		basketRepository.save(basket);
	}
}
