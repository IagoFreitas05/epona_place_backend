package main.place.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String value;

    @Column(name = "id_manager")
    private Integer idManager;

    @Column
    private String description;

    @Column(name = "SIZE")
    private String size;

    @Column(name="sale_price")
    private String salePrice;

    private String image;
}
