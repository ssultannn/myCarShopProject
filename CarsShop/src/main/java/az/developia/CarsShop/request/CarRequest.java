package az.developia.CarsShop.request;

import lombok.Data;

@Data
public class CarRequest {
	private String brand;
	private String model;
	private String description;
	private Double price;
	private Integer year;
	private String imgUrl;
    private Integer rating;
}
