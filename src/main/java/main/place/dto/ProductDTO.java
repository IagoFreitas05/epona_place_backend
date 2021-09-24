package main.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String category;
    private String value;
    private Integer idManager;
    private String description;
    private String size;
    private String salePrice;
    private Integer quantity;
    private String image;
}
