package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Address;
import main.place.entity.EntidadeDominio;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
import main.place.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/address")

public class AddressController {
    private final Facade facade;
    private final AddressRepository addressRepository;

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Address address){
        return facade.consultar(id, address);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(Address address){
        return facade.mostrarTodos(address);
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Address address){
        String res = facade.alterar(address);
        return res;
    }

    @PostMapping
    @CrossOrigin
    public String salvar(@RequestBody Address address){
        EntidadeDominio entity = facade.salvar(address);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
            return " ";
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Address address ){ facade.deletar(id,  address);}

    @GetMapping("findByUserId/{id}")
    @CrossOrigin
    public List<Address> findByUserId(@PathVariable Integer id){
        return addressRepository.findByIdUser(id);
    }
}
