package az.developia.CarsShop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "username не может быть пустым")
	private String username;
	@Size(min = 3, message = "пароль должен состоять из минимум трех элементов")
	@Size(max = 900, message = "пароль должен состоять из максимум 20 элементов")

	@NotBlank(message = "password не может быть пустым")
	private String password;
	private boolean enabled;
	@Email(message = "Должен соответсвовать формату email")
	private String email;

	@Pattern(regexp = "^\\+994\\s?(50|51|55|70|77)[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$", message = "Invalid phone number format")
	// +994555545770
	// +994 55 554 57 70
	// +994 55-554-57-70
	@NotBlank(message = "phoneNumber не может быть пустым")
	private String phoneNumber;

}
