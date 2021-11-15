package main.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleItemsCancelDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @Column( name = "product_image")
    private String product_image;

    private String quantity;

    private String value;

    @Column( name = "id_pedido" )
    private int idPedido;

    @Column( name =  "order_item_id" )
    private int order_item_id;
}
