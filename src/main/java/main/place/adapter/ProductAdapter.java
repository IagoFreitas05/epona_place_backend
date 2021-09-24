package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.ProductDTO;
import main.place.entity.Product;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ProductAdapter {
    public Product adapt(ProductDTO productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setSalePrice(productDTO.getSalePrice());
        product.setSize(productDTO.getSize());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setValue(productDTO.getValue());
        return product;
    }
}
