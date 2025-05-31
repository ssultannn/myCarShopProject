package az.developia.CarsShop.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarRequest {
	@NotBlank(message = "имя бренда не может быть пустым")
	private String brand;
	@NotBlank(message = "модель не может быть пустым")
	private String model;
	@Size(max = 1000,message = "описание не может быть пустым")
	private String description;
	
	@NotNull(message = "цена не может быть пустым")
	private Double price;
	
	@Min(value = 1900,message = "год машины должен превышать 1900")
	private Integer year;
	
	private String imgUrl;
	
	@Min(value = 1,message = "рейтинг не может быть меньше 1")
	@Max(value = 5,message = "рейтинг не может быть больше 5")
    private Integer rating;
}
