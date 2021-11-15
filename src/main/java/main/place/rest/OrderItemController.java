package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.dto.MultipleItemsCancelDTO;
import main.place.entity.*;
import main.place.facade.Facade;
import main.place.repository.MultipleItemsCancelDTORepository;
import main.place.repository.OrderItemRepository;
import main.place.repository.PurchaseOrderRepository;
import main.place.repository.RequestItemsCancelRepository;
import main.place.services.UserService;
import org.apache.coyote.Request;
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
    private final RequestItemsCancelRepository requestItemsCancelRepository;
    private final MultipleItemsCancelDTORepository multipleItemsCancelDTORepository;

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

    @GetMapping("cancelMultipleItems/{id}/{quantity}/{requestCancelId}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelMultipleItems(
            @PathVariable Integer id,
            @PathVariable Integer quantity,
            @PathVariable Integer requestCancelId ){

        Optional<OrderItem> orderItemOptinal = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptinal.get();

        if(orderItemSaved.getQuantity().equals(quantity)){
            orderItemSaved.setStatus("cancelado");
            facade.salvar(orderItemSaved);
        }else{
            orderItemSaved.setQuantity(orderItemSaved.getQuantity() - quantity);
            facade.salvar(orderItemSaved);
        }

        if(requestCancelId > 0){
            Optional<RequestItemsCancel> requestItemsCancel = requestItemsCancelRepository.findById(requestCancelId);
            RequestItemsCancel requestItemsCancelSaved = requestItemsCancel.get();
            requestItemsCancelSaved.setStatus("items_cancelados");
            facade.salvar(requestItemsCancelSaved);
        }

        Cupon cupon = new Cupon();
        cupon.setName(UUID.randomUUID().toString().replace('-','p'));
        cupon.setQuantity(1);
        cupon.setCountUsing(0);
        cupon.setType("DEVOLUCAO");
        cupon.setIsValid("true");
        cupon.setIdUser(orderItemSaved.getIdUser());
        float cupomValue = Float.parseFloat(orderItemSaved.getValue()) * quantity;
        cupon.setValue(Float.toString(cupomValue));

        facade.salvar(cupon);
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
        /*new commnetn*/

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(orderItemSaved.getIdPedido());
        PurchaseOrder purchaseOrderSaved = purchaseOrder.get();

        if(purchaseOrderSaved.getStatus().equals("andamento")){
            cancelItem(orderItemSaved.getId());
        }else {
            orderItemSaved.setStatus("cancelamento_solicitado");
            facade.salvar(orderItemSaved);
        }
    }

    @GetMapping("requestCancelByQuantity/{id}/{quantity}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestCancelByQuantity(@PathVariable Integer id, @PathVariable Integer quantity){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(orderItemSaved.getIdPedido());
        PurchaseOrder purchaseOrderSaved = purchaseOrder.get();

        if(purchaseOrderSaved.getStatus().equals("andamento")){
            cancelMultipleItems(orderItemSaved.getId(), quantity, 0);
        }else {
            RequestItemsCancel requestItemsCancel = new RequestItemsCancel();
            requestItemsCancel.setIdOrderItem(id);
            requestItemsCancel.setQuantity(quantity);
            requestItemsCancel.setStatus("cancelamento_solicitado");
            facade.salvar(requestItemsCancel);
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

    @GetMapping("returnQuantifiedItemByStatus/{status}")
    @CrossOrigin
    public Integer returnQuantifiedByStatus(@PathVariable String status){
        return orderItemRepository.findOrderItemByStatus(status).size();
    }

    @GetMapping("returnMultipleRequestCancelItem")
    @CrossOrigin
    public List<MultipleItemsCancelDTO> returnMultipleRequestCancelItem(){
        return multipleItemsCancelDTORepository.findMultipleItemsCancelDTO();
    }
}
