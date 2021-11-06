package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.PurchaseOrderAdapter;
import main.place.dto.*;
import main.place.entity.Cupon;
import main.place.entity.EntidadeDominio;
import main.place.entity.OrderItem;
import main.place.entity.PurchaseOrder;
import main.place.facade.Facade;
import main.place.repository.*;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/order")
public class OrdersController {
    private final Facade facade;
    private final UserService userService;
    private final PurchaseOrderAdapter orderAdapter;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PurcharseOrderByPeriodDTORepository orderByPeriodDTORepository;
    private final ProductsByPeriodDTORepository productsByPeriodDTORepository;
    private final SalesByCategoryDTORepository salesByCategoryDTORepository;
    private final ProductsSaleQuantityDTORepository productsSaleQuantityDTORepository;
    private Cupon cupon;

    @PostMapping
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody OrderDTO orderDTO) {
        orderDTO.setIdUser(userService.getLoggedUser().getId());
        PurchaseOrder order = orderAdapter.adapt(orderDTO);
        EntidadeDominio entityClient = facade.salvar(order);
        PurchaseOrder orderSaved = (PurchaseOrder) entityClient;

        for (int i = 0; i < orderDTO.getOrderItems().size(); i++) {
            orderDTO.getOrderItems().get(i).setIdUser(orderDTO.getIdUser());
            orderDTO.getOrderItems().get(i).setIdPedido(orderSaved.getId());
            facade.salvar(orderDTO.getOrderItems().get(i));
        }
        return " ";
    }

    @GetMapping
    @CrossOrigin
    public List<EntidadeDominio> index(PurchaseOrder purchaseOrder) {
        return facade.mostrarTodos(purchaseOrder);
    }

    @GetMapping("findByIdUser/{id}")
    @CrossOrigin
    public List<PurchaseOrder> findByIdUser(@PathVariable Integer id) {
        return purchaseOrderRepository.findByIdUserOrderByIdDesc(id);
    }

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> findById(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        return facade.consultar(id, purchaseOrder);
    }

    @GetMapping("findOrderItemsByIdOrder/{id}")
    @CrossOrigin
    public List<OrderItem> findOrderItemsByIdOrder(@PathVariable Integer id) {
        return orderItemRepository.findByIdPedido(id);
    }

    @GetMapping("cancelOrder/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("cancelado");

        /*create a cupon*/
        Cupon cupon = new Cupon();
        cupon.setName(UUID.randomUUID().toString().replace('-', 'p'));
        cupon.setQuantity(1);
        cupon.setCountUsing(0);
        cupon.setType("DEVOLUCAO");
        cupon.setIsValid("true");
        cupon.setIdUser(purchaseOrderSaved.getIdUser());
        cupon.setValue(purchaseOrderSaved.getTotalValue());

        facade.salvar(cupon);
        facade.alterar(purchaseOrderSaved);
    }

    @GetMapping("sendOrder/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendOrder(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("enviado");
        facade.alterar(purchaseOrderSaved);
    }

    @GetMapping("returnMade/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnMade(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setShippingStatus("retorno_enviado");
        facade.alterar(purchaseOrderSaved);
    }


    @GetMapping("confirmReceivement/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmReceivement(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("recebido");
        facade.alterar(purchaseOrderSaved);
    }

    @GetMapping("requestCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestCancel(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("aguardando_aprovacao");
        facade.alterar(purchaseOrderSaved);
    }

    @GetMapping("aprovedCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aprovedCancel(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("cancelamento_aprovado");
        facade.alterar(purchaseOrderSaved);
    }


    @GetMapping("deniedCancel/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deniedCancel(@PathVariable Integer id, PurchaseOrder purchaseOrder) {
        Optional<EntidadeDominio> entidadeDominio = facade.consultar(id, purchaseOrder);
        PurchaseOrder purchaseOrderSaved = (PurchaseOrder) entidadeDominio.get();
        purchaseOrderSaved.setStatus("cancelamento_negado");
        facade.alterar(purchaseOrderSaved);
    }

    @GetMapping("findByStatus/{status}")
    @CrossOrigin
    public List<PurchaseOrder> findByStatus(@PathVariable String status) {
        return purchaseOrderRepository.findPurchaseOrderByStatus(status);
    }

    @GetMapping("returnQuantifiedByStatus/{status}")
    @CrossOrigin
    public Integer returnQuantifiedByStatus(@PathVariable String status) {
        return purchaseOrderRepository.findPurchaseOrderByStatus(status).size();
    }

    @GetMapping("returnOrdersByPeriod")
    @CrossOrigin
    public List<PurchaseOrderByPeriodDTO> returnOrdersByPeriod() {
        return orderByPeriodDTORepository.findPurcharseOrderByPeriod();
    }

    @GetMapping("returnProductsByPeriod")
    @CrossOrigin
    public List<ProductsByPeriodDTO> returnProductsByPeriod(){
        return productsByPeriodDTORepository.findProductsByPeriod();
    }

    @GetMapping("returnSalesByCategory")
    @CrossOrigin
    public List<SalesByCategoryDTO> returnSalesByCategory(){
        return salesByCategoryDTORepository.findSalesByCategory();
    }

    @GetMapping("returnProductSaleQuantity")
    @CrossOrigin
    public List<ProductsSaleQuantityDTO> returnProductSaleQuantity(){
        return productsSaleQuantityDTORepository.findProductBySaleQuantity();
    }

    @PostMapping("getAnalysisByPeriod")
    @CrossOrigin
    public SearchDataAnalysisDTO returnAnalysisByPeriod(@RequestBody InputSearchAnalysisDTO inputSearchAnalysisDTO){
        SearchDataAnalysisDTO response = new SearchDataAnalysisDTO();
        response.setPurchaseOrderByPeriodDTO(orderByPeriodDTORepository
                .findPurcharseOrderByLimitedPeriod(
                    inputSearchAnalysisDTO.getStartPeriod(),
                    inputSearchAnalysisDTO.getEndsPeriod())
        );

        response.setProductsByPeriodDTO(productsByPeriodDTORepository
                .findProductsByLimitedPeriod(
                    inputSearchAnalysisDTO.getStartPeriod(),
                    inputSearchAnalysisDTO.getEndsPeriod())
        );

        return response;
    }
}
