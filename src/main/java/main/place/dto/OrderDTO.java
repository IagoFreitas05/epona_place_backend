package main.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.place.entity.OrderItem;

import java.time.LocalDateTime;
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
    private LocalDateTime date;
    private String status;
    private List<OrderItem> orderItems;
}
