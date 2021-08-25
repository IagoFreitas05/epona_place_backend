package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Address;
import main.place.entity.EntidadeDominio;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/address")

public class AddressController {
    private final Facade facade;

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Address address){
        return facade.consultar(id, address);
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Address address){
        return facade.alterar(address);
    }

    @PostMapping
    @CrossOrigin
    public String salvar(@RequestBody Address address){
        EntidadeDominio entity = facade.salvar(address);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return "salvo com sucesso";
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Address address ){ facade.deletar(id,  address);}
}
