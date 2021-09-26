package main.place.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.place.dto.OrderDTO;
import main.place.entity.PurchaseOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
public class PurchaseOrderAdapter {

    public PurchaseOrder adapt(OrderDTO orderDTO){
        PurchaseOrder order = new PurchaseOrder();
        order.setData(LocalDateTime.now());
        order.setIdAddress(orderDTO.getIdAddress());
        order.setIdCreditCard(orderDTO.getIdCreditCard());
        order.setIdCupom(orderDTO.getIdCupom());
        order.setPaymentType(orderDTO.getPaymentType());
        order.setStatus(orderDTO.getStatus());
        order.setIdUser(orderDTO.getIdUser());
        order.setTotalValue(orderDTO.getTotalValue());
        return order;
    }
}
