package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.ProductAdapter;
import main.place.adapter.ProductInvetoryAdapter;
import main.place.dto.ProductDTO;
import main.place.entity.*;
import main.place.facade.Facade;
import main.place.repository.ProductInvetoryRepository;
import main.place.repository.ProductRepository;
import main.place.services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/product")
public class ProductController {
    private final Facade facade;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductAdapter productAdapter;
    private final ProductInvetoryAdapter productInvetoryAdapter;


    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody ProductDTO productDTO){
        Product product = productAdapter.adapt(productDTO);
        ProductInvetory productInvetory = productInvetoryAdapter.adapt(productDTO);
        productInvetory.setIdManager(userService.getLoggedUser().getId());

        product.setIdManager(userService.getLoggedUser().getId());
        EntidadeDominio entity = facade.salvar(product);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }

        Product productSaved = (Product) entity;
        productInvetory.setIdProduct(productSaved.getId());
        facade.salvar(productInvetory);

        return "sucesso";
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Product product){
        product.setIdManager(userService.getLoggedUser().getId());
        String res = facade.alterar(product);
        return res;
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Product product ){ facade.deletar(id,  product);}

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Product product){
        return facade.consultar(id, product);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(Product product){
        return facade.mostrarTodos(product);
    }

    @GetMapping("findByIdManager/{id}")
    @CrossOrigin
    public List<Product> mostrarPorIdUser(@PathVariable Integer id, CreditCard creditCard){return productRepository.findByIdManager(id); }

    @GetMapping("disable/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable Integer id, Product Productparam){
       Optional<EntidadeDominio> productSaved = facade.consultar(id, Productparam);
       Product product = (Product) productSaved.get();
       product.setStatus("inativo");
       facade.salvar(product);
    }

    @GetMapping("activate/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Integer id, Product Productparam){
        Optional<EntidadeDominio> productSaved = facade.consultar(id, Productparam);
        Product product = (Product) productSaved.get();
        product.setStatus("ativo");
        facade.salvar(product);
    }
}
