package az.developia.CarsShop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CarsShop.entity.UserEntity;
import az.developia.CarsShop.request.UserLoginRequest;
import az.developia.CarsShop.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public void register(@RequestBody UserEntity userEntity) {
		userService.register(userEntity);
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest userLoginRequest) {
		String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());

		return ResponseEntity.ok(Map.of("token", token));
	}
}
