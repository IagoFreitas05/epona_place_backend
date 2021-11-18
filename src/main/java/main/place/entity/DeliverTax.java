package main.place.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeliverTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "QUANTITY_ITEM")
    private Integer quantityItem;

    @Column( name = "TAX_VALUE")
    private String taxValue;
}
