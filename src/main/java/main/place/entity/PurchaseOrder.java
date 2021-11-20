package main.place.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder extends EntidadeDominio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_address")
    private Integer idAddress;

    @Column(name = "id_credit_card")
    private Integer idCreditCard;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "id_cupom")
    private Integer idCupom;

    @Column(name = "total_value")
    private String totalValue;

    @Column
    private String status;

    @Column (name ="shipping_status")
    private String shippingStatus;

    @Column
    private LocalDateTime data;
}
