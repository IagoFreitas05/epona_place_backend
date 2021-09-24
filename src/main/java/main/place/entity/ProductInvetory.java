package main.place.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInvetory extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_manager")
    private Integer idManager;
    @Column(name = "original_quantity")
    private Integer originalQuantity;
    @Column(name = "current_quantity")
    private Integer currentQuantity;
    @Column(name = "id_product")
    private Integer idProduct;
}
