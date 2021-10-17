package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Cupon;
import main.place.entity.EntidadeDominio;
import main.place.entity.OrderItem;
import main.place.entity.PurchaseOrder;
import main.place.facade.Facade;
import main.place.repository.OrderItemRepository;
import main.place.repository.PurchaseOrderRepository;
import main.place.services.UserService;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/OrderItem")
public class OrderItemController {
    private final Facade facade;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @GetMapping("cancelItem/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelItem(@PathVariable Integer id){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();
        orderItemSaved.setStatus("cancelado");

        /*create a cupon*/
        Cupon cupon = new Cupon();
        cupon.setName(UUID.randomUUID().toString().replace('-','p'));
        cupon.setQuantity(1);
        cupon.setCountUsing(0);
        cupon.setType("DEVOLUCAO");
        cupon.setIsValid("true");
        cupon.setIdUser(orderItemSaved.getIdUser());
        cupon.setValue(orderItemSaved.getValue());

        facade.salvar(cupon);
        facade.salvar(orderItemSaved);
    }

    @GetMapping("findByStatus/{status}")
    @CrossOrigin
    public List<OrderItem> findByStatus(@PathVariable String status){
        return orderItemRepository.findOrderItemByStatus(status);
    }

    @GetMapping("requestCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestCancel(@PathVariable Integer id){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(orderItemSaved.getIdPedido());
        PurchaseOrder purchaseOrderSaved = purchaseOrder.get();

        if(purchaseOrderSaved.getStatus().equals("andamento")){
            cancelItem(orderItemSaved.getId());
        }else {
            orderItemSaved.setStatus("cancelamento_solicitado");
            facade.salvar(orderItemSaved);
        }
    }

    @GetMapping("aprovedCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aprovedCancel(@PathVariable Integer id){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();
        orderItemSaved.setStatus("cancelamento_aprovado");
        facade.salvar(orderItemSaved);
    }

    @GetMapping("deniedCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void daniedCancel(@PathVariable Integer id){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();
        orderItemSaved.setStatus("cancelamento_negado");
        facade.salvar(orderItemSaved);
    }

    @GetMapping("returnMade/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnMade(@PathVariable Integer id, OrderItem purchaseOrder){
        Optional<EntidadeDominio> entidadeDominio =  facade.consultar(id, purchaseOrder);
        OrderItem purchaseOrderSaved = (OrderItem) entidadeDominio.get();
        purchaseOrderSaved.setShippingStatus("retorno_enviado");
        facade.alterar(purchaseOrderSaved);
    }
}
