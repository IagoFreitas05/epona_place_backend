package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ProductDTO;
import main.place.entity.ProductInvetory;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ProductInvetoryAdapter {
    public ProductInvetory adapt(ProductDTO productDTO){
        ProductInvetory productInvetory = new ProductInvetory();
        productInvetory.setCurrentQuantity(productDTO.getQuantity());
        productInvetory.setOriginalQuantity(productDTO.getQuantity());

        return productInvetory;
    }
}
