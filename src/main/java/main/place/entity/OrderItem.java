package main.place.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column( name = "id_user")
    private Integer idUser;

    @Column( name = "id_manager")
    private Integer idManager;

    @Column(name = "id_produto")
    private Integer idProduto;

    @Column( name = "unit_price")
    private String unit_price;

    @Column
    private Integer quantity;
}
