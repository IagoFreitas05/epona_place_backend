package main.place.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliverTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "QUANTITY_ITEM")
    private Integer quantityItem;

    @Column( name = "TAX_VALUE")
    private String taxValue;
}
