package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.CreditCard;
import main.place.entity.EntidadeDominio;
import main.place.entity.Product;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
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

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Product product){
        product.setIdManager(userService.getLoggedUser().getId());
        EntidadeDominio entity = facade.salvar(product);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
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
}
