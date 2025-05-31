package az.developia.CarsShop.response;

import lombok.Data;

@Data
public class BasketResponse {
	private Long id;
	private String brand;
	private String model;
	private String description;
	private Double price;
	private Integer year;
	private String imgUrl;
    private Integer rating;
	private Long ownerId;
	private Integer quantity;
}
