package main.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.place.entity.OrderItem;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer idUser;
    private Integer idAddress;
    private Integer idCreditCard;
    private String  paymentType;
    private Integer idCupom;
    private String  totalValue;
    private Date    date;
    private List<OrderItem> orderItems;
}
